// Author: Aurelio Hevi Alfons
// Assignment 2
package com.example.api_project_aurelio

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

// Add this to ensure the app runs with DI
// We need to add this to every activity or fragment that use Dependency Injection
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up NavController and BottomNavigationView
        val hostFragment = supportFragmentManager.findFragmentById(R.id.navhost) as NavHostFragment
        val navController = hostFragment.navController
        val navbar = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Make sure to reset listener to avoid double insets
        navbar.setOnApplyWindowInsetsListener(null)

        // Connect BottomNavigationView with NavController
        navbar.setupWithNavController(navController)

        // Function to select the right clicked button
        navbar.setOnItemSelectedListener { item ->
            if (item.itemId != navbar.selectedItemId) {
                // navbar.popBackStack(item.itemId, inclusive = false)
                val fragmentId = when(item.itemId) {
                    R.id.Navigation_logout -> R.id.fragmentLogin
                    R.id.Navigation_dash -> R.id.fragmentDashboard
                    R.id.Navigation_details -> R.id.fragmentDetails
                    else -> R.id.fragmentLogin
                }
                navController.navigate(fragmentId)
            }
            true
        }

        // Show or hide BottomNavigationView based on the current fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentLogin -> navbar.visibility = View.GONE
                R.id.fragmentDashboard, R.id.fragmentDetails -> navbar.visibility = View.VISIBLE
                else -> navbar.visibility = View.VISIBLE
            }
        }

        // Optionally handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}