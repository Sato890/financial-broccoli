package com.example.financial_broccoli.feature_expense.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.financial_broccoli.feature_expense.ExpenseViewModel

@Composable
fun ExpensesScreen(viewModel: ExpenseViewModel) {

    val expenses by viewModel.expenses.collectAsState()
    val totalExpense by viewModel.totalExpense.observeAsState(0.0)
    var filledText by remember { mutableStateOf(value = "") }
    val currencySymbol by remember { mutableStateOf("€") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text("Total: $totalExpense $currencySymbol")

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
                modifier = Modifier.weight(1F).fillMaxWidth()
            )

            Spacer(modifier = Modifier.width(16.dp))

            SumButton(
                onClick = {
                    if (filledText.isNotBlank()) {
                        val amount = filledText.toDoubleOrNull()
                        if (amount != null) {
                            viewModel.addExpense(amount) // Save to DB
                            filledText = ""
                        }
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
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        modifier = modifier
    )
}
