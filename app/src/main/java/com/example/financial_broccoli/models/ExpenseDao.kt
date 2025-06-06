package com.example.financial_broccoli.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getAllExpenses  (): List<Expense>

    @Query("SELECT SUM(amount) FROM expense")
    fun getTotalExpense (): LiveData<Double?>

    @Insert
    fun addExpense(vararg expense: Expense)

    @Delete
    fun delete(user: Expense)
}