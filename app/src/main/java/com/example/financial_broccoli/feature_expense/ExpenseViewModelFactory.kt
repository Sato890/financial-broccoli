package com.example.financial_broccoli.feature_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financial_broccoli.feature_expense.data.ExpenseRepository

class ExpenseViewModelFactory(private val repository: ExpenseRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpenseViewModel(repository) as T
    }

}