package com.taller.gestordeherramientas.database.daos

import androidx.room.*
import com.taller.gestordeherramientas.database.entities.Tool
import kotlinx.coroutines.flow.Flow

@Dao
interface ToolDao {
    @Query("SELECT * FROM tools")
    fun getAllTools(): Flow<List<Tool>>

    @Query("SELECT * FROM tools WHERE cantidadDisponible > 0")
    fun getAvailableTools(): Flow<List<Tool>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tool: Tool)

    @Update
    suspend fun update(tool: Tool)

    @Delete
    suspend fun delete(tool: Tool)
}
