package com.taller.gestordeherramientas.database.daos

import androidx.room.*
import com.taller.gestordeherramientas.database.entities.Loan
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanDao {
    @Query("SELECT * FROM loans WHERE usuarioId = :userId")
    fun getLoansByUser(userId: Int): Flow<List<Loan>>

    @Query("SELECT * FROM loans")
    fun getAllLoans(): Flow<List<Loan>>

    @Insert
    suspend fun insertLoan(loan: Loan)

    @Update
    suspend fun updateLoan(loan: Loan)

    @Delete
    suspend fun deleteLoan(loan: Loan)
}
