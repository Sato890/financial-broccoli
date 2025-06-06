package com.example.financial_broccoli.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val eid: Int? = null,
    @ColumnInfo(name = "amount") val amount: Double? = 0.0,
) 

