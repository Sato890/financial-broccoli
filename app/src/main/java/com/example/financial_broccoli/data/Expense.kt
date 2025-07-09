package com.example.financial_broccoli.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val eid: Int? = null,
    @ColumnInfo(name = "amount") val amount: Double? = 0.0,
)