package com.example.financial_broccoli.models

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Expense::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}