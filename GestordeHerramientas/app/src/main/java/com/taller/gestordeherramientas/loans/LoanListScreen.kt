package com.taller.gestordeherramientas.loans

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import com.taller.gestordeherramientas.database.AppDatabase
import com.taller.gestordeherramientas.database.entities.Loan
import com.taller.gestordeherramientas.database.repositories.LoanRepository
import com.taller.gestordeherramientas.loans.components.LoanItem
import com.taller.gestordeherramientas.viewmodels.LoanViewModel
import com.taller.gestordeherramientas.viewmodels.LoanViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LoanListScreen(
    usuarioId: Int,
    context: Context = LocalContext.current
) {
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { LoanRepository(db.loanDao()) }
    val viewModel: LoanViewModel = viewModel(factory = LoanViewModelFactory(repository))

    val loans by viewModel.loans.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadLoansByUser(usuarioId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis préstamos") })
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(loans) { loan ->
                LoanItem(loan = loan, onDevolver = {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val now = dateFormat.format(Date())
                    val actualizado = loan.copy(estado = "devuelto", fechaDevolucion = now)
                    viewModel.updateLoan(actualizado)
                })
            }
        }
    }
}
