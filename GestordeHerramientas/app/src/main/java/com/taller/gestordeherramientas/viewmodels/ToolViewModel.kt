package com.taller.gestordeherramientas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taller.gestordeherramientas.database.entities.Tool
import com.taller.gestordeherramientas.database.repositories.ToolRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ToolViewModel(private val repository: ToolRepository) : ViewModel() {

    private val _tools = MutableStateFlow<List<Tool>>(emptyList())
    val tools: StateFlow<List<Tool>> = _tools.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAvailableTools()
                .collect { toolList -> _tools.value = toolList }
        }
    }

    fun addTool(tool: Tool) {
        viewModelScope.launch {
            repository.insertTool(tool)
        }
    }

    fun updateTool(tool: Tool) {
        viewModelScope.launch {
            repository.updateTool(tool)
        }
    }

    fun deleteTool(tool: Tool) {
        viewModelScope.launch {
            repository.deleteTool(tool)
        }
    }
}
