package com.example.api_project_aurelio.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
// API response of how manny items in the list
data class ApiResponse(
    @Json(name = "entities") val entities: List<ArtworkEntity>,
    @Json(name = "entityTotal") val entityTotal: Int
)