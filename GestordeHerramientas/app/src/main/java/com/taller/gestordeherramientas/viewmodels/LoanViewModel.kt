package com.taller.gestordeherramientas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taller.gestordeherramientas.database.entities.Loan
import com.taller.gestordeherramientas.database.repositories.LoanRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoanViewModel(private val repository: LoanRepository) : ViewModel() {

    private val _loans = MutableStateFlow<List<Loan>>(emptyList())
    val loans: StateFlow<List<Loan>> = _loans.asStateFlow()

    fun loadLoansByUser(userId: Int) {
        viewModelScope.launch {
            repository.getLoansByUser(userId).collect {
                _loans.value = it
            }
        }
    }

    fun createLoan(loan: Loan) {
        viewModelScope.launch {
            repository.insertLoan(loan)
        }
    }

    fun updateLoan(loan: Loan) {
        viewModelScope.launch {
            repository.updateLoan(loan)
        }
    }

    fun deleteLoan(loan: Loan) {
        viewModelScope.launch {
            repository.deleteLoan(loan)
        }
    }
}
