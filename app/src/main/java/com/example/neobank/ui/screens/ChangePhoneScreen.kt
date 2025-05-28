package com.example.neobank.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.foundation.text.KeyboardOptions // ✅ THIS IS CORRECT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePhoneScreen(navController: NavController) {
    var newPhone by remember { mutableStateOf("") }
    var otpCode by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var otpError by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Change Phone Number") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = newPhone,
                onValueChange = {
                    newPhone = it
                    phoneError = null
                },
                label = { Text("New Phone Number") },
                singleLine = true,
                isError = phoneError != null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (phoneError != null) {
                Text(text = phoneError!!, color = MaterialTheme.colorScheme.error)
            }

            OutlinedTextField(
                value = otpCode,
                onValueChange = {
                    otpCode = it
                    otpError = null
                },
                label = { Text("OTP Code") },
                singleLine = true,
                isError = otpError != null,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )
            if (otpError != null) {
                Text(text = otpError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    var hasError = false
                    if (newPhone.length < 10) {
                        phoneError = "Please enter a valid phone number"
                        hasError = true
                    }
                    if (otpCode.length < 4) {
                        otpError = "Enter a valid 4-6 digit OTP"
                        hasError = true
                    }

                    if (!hasError) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Phone number updated")
                        }
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Phone Number")
            }
        }
    }
}
