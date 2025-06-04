package com.example.financial_broccoli

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancialBroccoliTheme {
                AddExpenseScreen()
            }
        }
    }
}

@Composable
fun AddExpenseScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var filledText by remember {mutableStateOf(value = "")}
        val currencySymbol by remember { mutableStateOf("€")}
        val errorText by remember { mutableStateOf("error") }

        Text("Total: 0.0 $currencySymbol")

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            CurrencyInputField(
                value = filledText,
                onValueChange = {filledText = it},
                label = "Enter your expense",
                currencySymbol = currencySymbol,
                isError = false,
                errorText = errorText,
            )

            Spacer(modifier = Modifier.width(16.dp))

            SumButton(
                onClick = {/*TODO*/}
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
    isError: Boolean,
    errorText: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        suffix = { Text(currencySymbol) },
        isError = isError,
        supportingText = { if (isError) Text(errorText) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        modifier = modifier
    )
}
