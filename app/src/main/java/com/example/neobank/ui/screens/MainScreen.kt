package com.example.neobank.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.neobank.ui.navigation.AppNavHost
import com.example.neobank.ui.viewmodel.AuthViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val items = listOf("home", "wallet", "send", "profile")
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    val icon = when (item) {
                        "home" -> Icons.Filled.Home
                        "wallet" -> Icons.Filled.AccountBalanceWallet
                        "send" -> Icons.Filled.Send
                        "profile" -> Icons.Filled.AccountCircle
                        else -> Icons.Filled.Home
                    }

                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item) {
                                popUpTo("home") { inclusive = false }
                                launchSingleTop = true
                            }
                        },
                        label = { Text(item.replaceFirstChar { it.uppercase() }) },
                        icon = { Icon(icon, contentDescription = null) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavHost(
                navController = navController,
                authViewModel = authViewModel
            )

        }
    }
}
