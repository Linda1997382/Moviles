package com.taller.gestordeherramientas.auth

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taller.gestordeherramientas.database.AppDatabase
import com.taller.gestordeherramientas.database.repositories.UserRepository

@Composable
fun LoginScreen(
    onLoginSuccess: (userId: Int, userType: String) -> Unit,
    context: Context = LocalContext.current
) {
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { UserRepository(db.userDao()) }
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(repository))

    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }

    val errorMessage by viewModel.errorMessage.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()

    currentUser?.let { user ->
        LaunchedEffect(user) {
            onLoginSuccess(user.id, user.tipo)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            viewModel.login(correo.trim(), contraseña.trim())
        }) {
            Text("Iniciar sesión")
        }
    }
}
