package com.taller.gestordeherramientas.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loans")
data class Loan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val herramientaId: Int,
    val usuarioId: Int,
    val cantidad: Int,
    val fechaPrestamo: String,
    val fechaDevolucion: String?,
    val estado: String // Ej: "pendiente", "devuelto", "dañado"
)
