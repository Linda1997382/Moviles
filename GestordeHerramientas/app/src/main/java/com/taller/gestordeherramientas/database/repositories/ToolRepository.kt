package com.taller.gestordeherramientas.database.repositories

import com.taller.gestordeherramientas.database.daos.ToolDao
import com.taller.gestordeherramientas.database.entities.Tool
import kotlinx.coroutines.flow.Flow

class ToolRepository(private val toolDao: ToolDao) {

    fun getAllTools(): Flow<List<Tool>> = toolDao.getAllTools()

    fun getAvailableTools(): Flow<List<Tool>> = toolDao.getAvailableTools()

    suspend fun insertTool(tool: Tool) = toolDao.insert(tool)

    suspend fun updateTool(tool: Tool) = toolDao.update(tool)

    suspend fun deleteTool(tool: Tool) = toolDao.delete(tool)
}
