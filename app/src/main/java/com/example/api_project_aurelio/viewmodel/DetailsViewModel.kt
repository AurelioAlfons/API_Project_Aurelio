package com.example.api_project_aurelio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_project_aurelio.data.ArtworkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _selectedArtwork = MutableLiveData<ArtworkEntity?>()
    val selectedArtwork: LiveData<ArtworkEntity?> = _selectedArtwork

    // Set the selected artwork, can be called when data is passed in the fragment
    fun setSelectedArtwork(artwork: ArtworkEntity) {
        _selectedArtwork.value = artwork
    }
}
