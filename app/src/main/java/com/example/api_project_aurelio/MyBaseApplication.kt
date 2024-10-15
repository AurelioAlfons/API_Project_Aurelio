package com.example.api_project_aurelio

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyBaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("nit3213", "Application onCreate was called")
    }
}