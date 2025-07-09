package com.example.financial_broccoli.feature_expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_broccoli.feature_expense.data.Expense
import com.example.financial_broccoli.feature_expense.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    val expenses: StateFlow<List<Expense>> = repository.allExpenses.map {
        it.sortedByDescending {expense -> expense.eid}}
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    val totalExpense: LiveData<Double?> = repository.getTotalExpense()

    fun addExpense(amount: Double) = viewModelScope.launch {
        repository.addExpense(Expense(amount = amount))
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.deleteExpense(expense)
    }

}