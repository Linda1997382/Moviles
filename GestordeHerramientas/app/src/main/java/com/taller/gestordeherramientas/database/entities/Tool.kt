¿package com.taller.gestordeherramientas.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tools")
data class Tool(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val cantidadTotal: Int,
    val cantidadDisponible: Int,
    val imagenUrl: String
)
