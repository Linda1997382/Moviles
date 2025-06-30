package com.taller.gestordeherramientas.tools.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.taller.gestordeherramientas.database.entities.Tool

@Composable
fun ToolCard(tool: Tool, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(tool.imagenUrl),
                contentDescription = tool.nombre,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = tool.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "Disponibles: ${tool.cantidadDisponible}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
