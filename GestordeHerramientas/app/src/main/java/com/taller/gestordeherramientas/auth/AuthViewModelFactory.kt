package com.taller.gestordeherramientas.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taller.gestordeherramientas.database.repositories.UserRepository

class AuthViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
