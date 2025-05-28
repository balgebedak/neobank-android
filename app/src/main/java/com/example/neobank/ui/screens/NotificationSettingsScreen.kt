package com.example.neobank.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.neobank.ui.components.SettingSwitchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen(navController: NavController) {
    var txAlerts by remember { mutableStateOf(true) }
    var promoUpdates by remember { mutableStateOf(false) }
    var securityAlerts by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            SettingSwitchItem(
                title = "Transaction Alerts",
                checked = txAlerts,
                onCheckedChange = { txAlerts = it }
            )

            SettingSwitchItem(
                title = "Promotional Updates",
                checked = promoUpdates,
                onCheckedChange = { promoUpdates = it }
            )

            SettingSwitchItem(
                title = "Security Alerts",
                checked = securityAlerts,
                onCheckedChange = { securityAlerts = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}
