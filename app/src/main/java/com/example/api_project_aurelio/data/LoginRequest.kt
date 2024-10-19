package com.example.api_project_aurelio.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

data class LoginRequest(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String
)
