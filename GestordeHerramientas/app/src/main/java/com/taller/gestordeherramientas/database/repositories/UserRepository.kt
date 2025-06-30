package com.taller.gestordeherramientas.database.repositories

import com.taller.gestordeherramientas.database.daos.UserDao
import com.taller.gestordeherramientas.database.entities.User

class UserRepository(private val dao: UserDao) {

    suspend fun login(correo: String, contraseña: String): User? =
        dao.login(correo, contraseña)

    suspend fun register(user: User): Long =
        dao.register(user)

    suspend fun getUserByCorreo(correo: String): User? =
        dao.getUserByCorreo(correo)
}
