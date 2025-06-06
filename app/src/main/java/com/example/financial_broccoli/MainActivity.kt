package com.example.financial_broccoli

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.financial_broccoli.ui.theme.FinancialBroccoliTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextAlign
import androidx.room.Room
import com.example.financial_broccoli.models.AppDatabase
import com.example.financial_broccoli.models.Expense
import com.example.financial_broccoli.models.ExpenseDao


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*TODO remove fallbackToDestructiveMigration*/

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration(true)
            .build()

        val dao = db.expenseDao()

        setContent {

            val totalExpense: Double? by dao.getTotalExpense().observeAsState()
            val currentTotalExpense = totalExpense ?: 0.0

            FinancialBroccoliTheme {
                AddExpenseScreen(dao, currentTotalExpense)

            }
        }
    }
}

@Composable
fun AddExpenseScreen(dao: ExpenseDao, total: Double?){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var filledText by remember {mutableStateOf(value = "")}
        val currencySymbol by remember { mutableStateOf("€")}

        Text("Total: $total $currencySymbol")

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            CurrencyInputField(
                value = filledText,
                onValueChange = { newValue ->
                    val filteredValue = newValue.filter { it.isDigit() || it == '.' }
                    if (filteredValue.count { it == '.' } <= 1) {
                        filledText = filteredValue
                    }
                },
                label = "Enter your expense",
                currencySymbol = currencySymbol,
            )

            Spacer(modifier = Modifier.width(16.dp))

            SumButton(
                onClick = {
                    if (filledText.toDoubleOrNull() != null) {
                        val expense = Expense(amount = filledText.toDouble())

                        Thread {
                            dao.addExpense(expense)

                            val expenses: List<Expense> = dao.getAllExpenses()

                            for (e in expenses){
                                Log.d(TAG, "amount: $e")
                            }
                        }.start()

                    }
                }
            )

        }

    }
}

@Composable
fun SumButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text("Add")
    }
}

@Composable
fun CurrencyInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    currencySymbol: String,
    modifier: Modifier = Modifier
) {
    TextField(
        
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        suffix = { Text(currencySymbol) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        modifier = modifier
    )
}
