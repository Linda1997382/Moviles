package com.taller.gestordeherramientas.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val correo: String,
    val contraseña: String,
    val tipo: String // "admin" o "empleado"
)
