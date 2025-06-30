package com.taller.gestordeherramientas.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taller.gestordeherramientas.database.entities.User
import com.taller.gestordeherramientas.database.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(correo: String, contraseña: String) {
        viewModelScope.launch {
            val user = repository.login(correo, contraseña)
            if (user != null) {
                _currentUser.value = user
                _errorMessage.value = null
            } else {
                _errorMessage.value = "Correo o contraseña incorrectos"
            }
        }
    }

    fun register(nombre: String, correo: String, contraseña: String, tipo: String) {
        viewModelScope.launch {
            val exists = repository.getUserByCorreo(correo)
            if (exists != null) {
                _errorMessage.value = "El usuario ya existe"
            } else {
                val user = User(nombre = nombre, correo = correo, contraseña = contraseña, tipo = tipo)
                repository.register(user)
                _errorMessage.value = null
            }
        }
    }

    fun logout() {
        _currentUser.value = null
    }
}
