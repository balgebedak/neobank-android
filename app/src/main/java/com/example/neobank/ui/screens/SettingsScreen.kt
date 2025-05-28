package com.example.neobank.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.neobank.ui.components.ProfileOptionCard


@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ProfileOptionCard(
            title = "Security Settings",
            icon = Icons.Default.Security
        ) {
            navController.navigate("security_settings")
        }

        ProfileOptionCard(
            title = "Notification Settings",
            icon = Icons.Default.Notifications
        ) {
            navController.navigate("notification_settings")
        }


        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "App Version: 1.0.0",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 24.dp)
        )
    }
}
