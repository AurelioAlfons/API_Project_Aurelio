package com.example.api_project_aurelio.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

// Entities list receive from the API keypass
// They're all String except for year
data class ArtworkEntity(
    @Json(name = "artworkTitle") val artworkTitle: String,
    @Json(name = "artist") val artist: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "year") val year: Int,
    @Json(name = "description") val description: String
)