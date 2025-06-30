package com.taller.gestordeherramientas.loans

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.taller.gestordeherramientas.database.AppDatabase
import com.taller.gestordeherramientas.database.entities.Loan
import com.taller.gestordeherramientas.database.repositories.LoanRepository
import com.taller.gestordeherramientas.viewmodels.LoanViewModel
import com.taller.gestordeherramientas.viewmodels.LoanViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LoanRequestScreen(
    herramientaId: Int,
    usuarioId: Int,
    context: Context = LocalContext.current,
    onLoanCreated: () -> Unit
) {
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { LoanRepository(db.loanDao()) }
    val viewModel: LoanViewModel = viewModel(factory = LoanViewModelFactory(repository))

    var cantidad by remember { mutableStateOf("") }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val now = dateFormat.format(Date())

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Solicitar préstamo", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad a solicitar") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (cantidad.isNotBlank() && cantidad.toIntOrNull() != null) {
                    val loan = Loan(
                        herramientaId = herramientaId,
                        usuarioId = usuarioId,
                        cantidad = cantidad.toInt(),
                        fechaPrestamo = now,
                        fechaDevolucion = null,
                        estado = "pendiente"
                    )
                    viewModel.createLoan(loan)
                    onLoanCreated()
                }
            }
        ) {
            Text("Confirmar solicitud")
        }
    }
}
