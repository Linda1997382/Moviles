package com.taller.gestordeherramientas.tools

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taller.gestordeherramientas.database.AppDatabase
import com.taller.gestordeherramientas.database.repositories.ToolRepository
import com.taller.gestordeherramientas.tools.components.ToolCard
import com.taller.gestordeherramientas.viewmodels.ToolViewModel
import com.taller.gestordeherramientas.viewmodels.ToolViewModelFactory

@Composable
fun ToolListScreen(context: android.content.Context, onToolSelected: (Int) -> Unit) {
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { ToolRepository(db.toolDao()) }
    val viewModel: ToolViewModel = viewModel(factory = ToolViewModelFactory(repository))

    val tools by viewModel.tools.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Herramientas Disponibles") })
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(tools) { tool ->
                ToolCard(tool = tool, onClick = { onToolSelected(tool.id) })
            }
        }
    }
}
