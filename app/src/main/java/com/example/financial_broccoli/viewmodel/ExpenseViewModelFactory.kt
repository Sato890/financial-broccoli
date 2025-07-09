package com.example.financial_broccoli.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financial_broccoli.data.ExpenseRepository

class ExpenseViewModelFactory(private val repository: ExpenseRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpenseViewModel(repository) as T
    }

}