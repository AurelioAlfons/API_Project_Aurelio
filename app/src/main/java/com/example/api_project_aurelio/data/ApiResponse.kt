package com.example.api_project_aurelio.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

// Data class stores data Only cannot be extend
// API response of how manny items in the list
data class ApiResponse(
    // List of the objects from  <ArtworkEntity>
    @Json(name = "entities") val entities: List<ArtworkEntity>,
    // Integer: total entity
    @Json(name = "entityTotal") val entityTotal: Int
)