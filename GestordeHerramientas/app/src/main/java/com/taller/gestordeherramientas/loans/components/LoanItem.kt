package com.taller.gestordeherramientas.loans.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taller.gestordeherramientas.database.entities.Loan

@Composable
fun LoanItem(
    loan: Loan,
    onDevolver: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID herramienta: ${loan.herramientaId}")
            Text("Cantidad: ${loan.cantidad}")
            Text("Estado: ${loan.estado}")
            Text("Fecha préstamo: ${loan.fechaPrestamo}")
            loan.fechaDevolucion?.let {
                Text("Devuelto: $it")
            }
            if (loan.estado != "devuelto") {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onDevolver) {
                    Text("Solicitar devolución")
                }
            }
        }
    }
}
