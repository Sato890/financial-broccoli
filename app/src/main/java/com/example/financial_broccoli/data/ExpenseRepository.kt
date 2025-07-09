package com.example.financial_broccoli.data

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {
    val allExpenses = dao.getAllExpenses()

    fun getTotalExpense() = dao.getTotalExpense()

    suspend fun addExpense(expense: Expense) = dao.addExpense(expense)
    suspend fun deleteExpense(expense: Expense) = dao.deleteExpense(expense)
}