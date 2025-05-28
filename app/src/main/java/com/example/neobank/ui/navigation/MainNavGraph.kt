package com.example.neobank.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.neobank.ui.screens.MainScreen
import com.example.neobank.ui.viewmodel.AuthViewModel

@Composable
fun MainNavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    MainScreen(
        navController = navController,
        authViewModel = authViewModel
    )
}
