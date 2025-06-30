package com.taller.gestordeherramientas.database.repositories

import com.taller.gestordeherramientas.database.daos.LoanDao
import com.taller.gestordeherramientas.database.entities.Loan
import kotlinx.coroutines.flow.Flow

class LoanRepository(private val dao: LoanDao) {

    fun getLoansByUser(userId: Int): Flow<List<Loan>> = dao.getLoansByUser(userId)

    fun getAllLoans(): Flow<List<Loan>> = dao.getAllLoans()

    suspend fun insertLoan(loan: Loan) = dao.insertLoan(loan)

    suspend fun updateLoan(loan: Loan) = dao.updateLoan(loan)

    suspend fun deleteLoan(loan: Loan) = dao.deleteLoan(loan)
}
