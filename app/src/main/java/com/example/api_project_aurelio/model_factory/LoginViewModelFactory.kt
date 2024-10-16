package com.example.api_project_aurelio.model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_aurelio.di.NetworkModule
import com.example.api_project_aurelio.viewmodel.LoginViewModel

// Purpose:
// - Create instance of ViewModel
// - Manual Dependency Injection (DI)
// - Will change

// CLASS: LoginViewModelFactory
class LoginViewModelFactory(private val networkModule: NetworkModule) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(networkModule) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
