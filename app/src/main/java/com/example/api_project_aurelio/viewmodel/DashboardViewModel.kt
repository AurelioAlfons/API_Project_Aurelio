package com.example.api_project_aurelio.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_project_aurelio.data.ArtworkEntity
import com.example.api_project_aurelio.network.RestfulApiDevService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: RestfulApiDevService,
    @ApplicationContext private val appContext: Context   // Inject the application context
) : ViewModel() {

    // MutableLiveData to hold artwork entities
    private val _artworkEntities = MutableLiveData<List<ArtworkEntity>>()
    val artworkEntities: LiveData<List<ArtworkEntity>> = _artworkEntities

    // MutableLiveData to handle error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchArtworks() {
        viewModelScope.launch {
            try {
                val sharedPreferences = appContext.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                val keypass = sharedPreferences.getString("keypass", "")

                if (!keypass.isNullOrEmpty()) {
                    val apiResponse = apiService.getArt(keypass)
                    _artworkEntities.postValue(apiResponse.entities)
                } else {
                    _errorMessage.postValue("Keypass is missing.")
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "An error occurred")
            }
        }
    }
}

