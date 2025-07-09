package com.example.financial_broccoli.feature_expense.data

class ExpenseRepository(private val dao: ExpenseDao) {
    val allExpenses = dao.getAllExpenses()

    fun getTotalExpense() = dao.getTotalExpense()

    suspend fun addExpense(expense: Expense) = dao.addExpense(expense)
    suspend fun deleteExpense(expense: Expense) = dao.deleteExpense(expense)
}