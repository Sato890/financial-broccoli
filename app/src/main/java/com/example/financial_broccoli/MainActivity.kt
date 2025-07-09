package com.example.financial_broccoli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.financial_broccoli.ui.theme.FinancialBroccoliTheme
import androidx.navigation.compose.rememberNavController
import com.example.financial_broccoli.data.AppDatabase
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.financial_broccoli.data.ExpenseRepository
import com.example.financial_broccoli.ui.ExpensesScreen
import com.example.financial_broccoli.viewmodel.ExpenseViewModel
import com.example.financial_broccoli.viewmodel.ExpenseViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*TODO remove fallbackToDestructiveMigration*/

        val db = AppDatabase.getInstance(applicationContext)
        val dao = db.expenseDao()
        val repository = ExpenseRepository(dao)
        val viewModelFactory = ExpenseViewModelFactory(repository)

        setContent {
            FinancialBroccoliTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.Expenses.route) {
                    composable(Screen.Expenses.route) {
                        val viewModel: ExpenseViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                            factory = viewModelFactory
                        )
                        ExpensesScreen(viewModel = viewModel)
                    }
                }

            }
        }
    }
}

sealed class Screen(val route: String, val label: String) {
    data object Expenses : Screen("expenses", "Expenses")
}




