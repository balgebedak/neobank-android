package com.example.neobank.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.neobank.ui.screens.AboutAppScreen
import com.example.neobank.ui.screens.ContactUsScreen
import com.example.neobank.ui.screens.DocumentsScreen
import com.example.neobank.ui.screens.EditProfileScreen
import com.example.neobank.ui.screens.HomeScreen
import com.example.neobank.ui.screens.NotificationSettingsScreen
import com.example.neobank.ui.screens.ProfileScreen
import com.example.neobank.ui.screens.SecuritySettingsScreen
import com.example.neobank.ui.screens.SettingsScreen
import com.example.neobank.ui.screens.SupportScreen
import com.example.neobank.ui.screens.WalletScreen
import com.example.neobank.ui.viewmodel.AuthViewModel
import com.example.neobank.ui.screens.P2PTransactionHistoryScreen
import com.example.neobank.ui.screens.CashtagPaymentScreen
import com.example.neobank.ui.screens.PaymentLinkScreen
import com.example.neobank.ui.screens.ChangePasswordScreen
import com.example.neobank.ui.screens.ChangeEmailScreen
import com.example.neobank.ui.screens.ChangePhoneScreen



@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("wallet") {
            WalletScreen()
        }
        composable("send") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("💸 Send Money Screen")
            }
        }
        composable("profile") {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("edit_profile") {
            EditProfileScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("security_settings") {
            SecuritySettingsScreen(navController = navController)
        }
        composable("notification_settings") {
            NotificationSettingsScreen(navController = navController)
        }
        composable("about_app") {
            AboutAppScreen(navController = navController)
        }
        composable("contact_us") {
            ContactUsScreen(navController = navController)
        }
        composable("support") {
            SupportScreen(navController = navController)
        }
        composable("documents") {
            DocumentsScreen(navController = navController)
        }
        composable("cashtag_payment") {
            CashtagPaymentScreen(navController = navController)
        }
        composable("payment_link") {
            PaymentLinkScreen(navController = navController)
        }
        composable("p2p_history") {
            P2PTransactionHistoryScreen(navController = navController)
        }
        composable("change_password_screen") {
            ChangePasswordScreen(navController = navController)
        }
        composable("change_email_screen") {
            ChangeEmailScreen(navController = navController)
        }
        composable("change_phone_screen") {
            ChangePhoneScreen(navController = navController)
        }

    }
}
