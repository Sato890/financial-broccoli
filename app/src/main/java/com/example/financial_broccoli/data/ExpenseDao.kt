package com.example.financial_broccoli.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses")
    fun getAllExpenses  (): Flow<List<Expense>>

    @Query("SELECT IFNULL(SUM(amount), 0) FROM expenses")
    fun getTotalExpense (): LiveData<Double?>

    @Insert
    suspend fun addExpense(vararg expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)
}