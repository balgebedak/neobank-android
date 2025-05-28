package com.example.neobank.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neobank.ui.screens.LoginScreen
import com.example.neobank.ui.viewmodel.AuthViewModel

@Composable
fun AuthNavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginClick = { email, password ->
                    authViewModel.login(email, password)
                },
                onNavigateToRegister = {
                    // You can ignore or leave this stubbed
                }
            )
        }
    }
}
