package com.example.financial_broccoli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.financial_broccoli.ui.ExpensesScreen
import com.example.financial_broccoli.ui.theme.FinancialBroccoliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancialBroccoliTheme {
                MainContent()
            }
        }
    }
}

sealed class Screen(val route: String, val label: String) {
    data object Expenses : Screen("expenses", "Expenses")
}

@Composable
fun MainContent(
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Expenses.route) {
        composable(Screen.Expenses.route) {
            ExpensesScreen()
        }
    }
}