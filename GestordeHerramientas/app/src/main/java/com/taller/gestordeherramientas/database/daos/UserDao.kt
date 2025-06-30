package com.taller.gestordeherramientas.database.daos

import androidx.room.*
import com.taller.gestordeherramientas.database.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE correo = :correo AND contraseña = :contraseña")
    suspend fun login(correo: String, contraseña: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: User): Long

    @Query("SELECT * FROM users WHERE correo = :correo")
    suspend fun getUserByCorreo(correo: String): User?
}
