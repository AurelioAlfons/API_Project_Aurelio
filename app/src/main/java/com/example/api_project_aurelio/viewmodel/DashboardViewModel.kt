package com.example.api_project_aurelio.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_project_aurelio.data.ArtworkEntity
import com.example.api_project_aurelio.network.RestfulApiDevService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: RestfulApiDevService,
    @ApplicationContext private val appContext: Context // Inject the application context
) : ViewModel() {

    // MutableStateFlow to hold artwork entities
    private val _artworkEntities = MutableStateFlow<List<ArtworkEntity>>(emptyList())
    val artworkEntities: StateFlow<List<ArtworkEntity>> = _artworkEntities

    // Fetch artwork data from API
    fun fetchArtworks() {
        viewModelScope.launch {
            try {
                val sharedPreferences = appContext.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                val keypass = sharedPreferences.getString("keypass", "")

                if (!keypass.isNullOrEmpty()) {
                    val apiResponse = apiService.getArt(keypass)
                    _artworkEntities.value = apiResponse.entities
                }
            } catch (e: Exception) {
                // Handle errors here if needed
            }
        }
    }
}
