package com.example.neobank.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.neobank.ui.viewmodel.AuthViewModel
import com.example.neobank.ui.components.ProfileOptionCard
import com.example.neobank.ui.screens.PaymentLinkScreen

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to log out?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        authViewModel.logout()
                    }
                ) {
                    Text("Log Out")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Account") },
            text = {
                Text("This action is permanent and cannot be undone.\n\nAre you sure you want to delete your account?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        // TODO: Add delete logic
                        println("Account deletion confirmed.")
                    }
                ) {
                    Text("Yes, Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ProfileOptionCard("Edit Profile", Icons.Default.Person) {
            navController.navigate("edit_profile")
        }

        ProfileOptionCard("Payment Methods", Icons.Default.CreditCard) {
            // TODO
        }
        ProfileOptionCard("P2P History", Icons.Default.History) {
            navController.navigate("p2p_history")
        }
        ProfileOptionCard("Settings", Icons.Default.Settings) {
            navController.navigate("settings")
        }

        ProfileOptionCard("Documents", Icons.Default.Folder) {
            navController.navigate("documents")
        }

        ProfileOptionCard("Support & Help", Icons.Default.Help) {
            navController.navigate("support")
        }

        ProfileOptionCard("Contact Us", Icons.Default.Email) {
            navController.navigate("contact_us")
        }

        ProfileOptionCard("About App", Icons.Default.Info) {
            navController.navigate("about_app")
        }

        ProfileOptionCard("Invite Friends", Icons.Default.Share) {
            // TODO
        }
        ProfileOptionCard("Cashtag Payment", Icons.Default.AttachMoney) {
            navController.navigate("cashtag_payment")
        }

        ProfileOptionCard("Payment Link", Icons.Default.Link) {
            navController.navigate("payment_link")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { showLogoutDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Log Out", color = MaterialTheme.colorScheme.onError)
        }

        Button(
            onClick = { showDeleteDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("Delete Account", color = MaterialTheme.colorScheme.onError)
        }
    }
}

@Composable
fun ProfileOptionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
