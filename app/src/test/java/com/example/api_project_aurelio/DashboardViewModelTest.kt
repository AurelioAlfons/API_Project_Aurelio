package com.example.api_project_aurelio

import android.content.Context
import android.content.SharedPreferences
import com.example.api_project_aurelio.data.ApiResponse
import com.example.api_project_aurelio.data.ArtworkEntity
import com.example.api_project_aurelio.network.RestfulApiDevService
import com.example.api_project_aurelio.viewmodel.DashboardViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

// Purpose:
// - Mocks API & SharedPreferences
// - Verifies fetchArtworks() updates artworkEntities correctly
// - Ensures ViewModel handles API response properly

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var apiService: RestfulApiDevService
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mockContext: Context

    private val testDispatcher = StandardTestDispatcher()

    // Example artwork entity for testing
    private val exampleArtworkEntity = ArtworkEntity(
        artworkTitle = "Mona Lisa",
        artist = "Leonardo da Vinci",
        medium = "Oil on canvas",
        year = 1503,
        description = "A portrait of a woman with a mysterious smile."
    )

    @Before
    fun setUp() {
        // Mock the apiService
        apiService = mockk()

        // Mock the Context and SharedPreferences
        mockContext = mockk()
        sharedPreferences = mockk()

        // Mock SharedPreferences behavior - Return keypass
        every { mockContext.getSharedPreferences(any(), any()) } returns sharedPreferences
        every { sharedPreferences.getString("keypass", "") } returns "valid_keypass"

        // Set up the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Mock the API response
        coEvery { apiService.getArt("valid_keypass") } returns ApiResponse(
            entities = listOf(
                exampleArtworkEntity.copy(artworkTitle = "The Starry Night"),
                exampleArtworkEntity.copy(artworkTitle = "The Persistence of Memory")
            ),
            entityTotal = 2
        )

        // Initialize ViewModel with mocked apiService and context
        viewModel = DashboardViewModel(apiService, mockContext)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetchArtworks updates artworkEntities with API data`() = runTest(testDispatcher) {
        // When fetching artworks
        viewModel.fetchArtworks()

        // Advance time to allow the ViewModel's coroutine to execute
        advanceUntilIdle()

        // Verify the StateFlow is updated with the API response
        val artworkList = viewModel.artworkEntities.value
        // Verify the size
        assertEquals(2, artworkList.size)
        // Verify first artwork title
        assertEquals("The Starry Night", artworkList[0].artworkTitle)
        // Verify second artwork title
        assertEquals("The Persistence of Memory", artworkList[1].artworkTitle)
    }
}
