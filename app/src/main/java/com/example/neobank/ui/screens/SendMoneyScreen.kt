package com.example.neobank.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.neobank.ui.viewmodel.AuthViewModel
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(navController: NavController) {
    var recipient by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var cashtag by remember { mutableStateOf("") }

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    var linkAmount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var paymentLink by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Send Money") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = recipient,
                onValueChange = { recipient = it },
                label = { Text("Recipient Username") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount (AFN)") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = cashtag,
                onValueChange = { cashtag = it },
                label = { Text("Or Enter Cashtag (e.g., \$jawid123)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // TODO: Handle send money
                    Toast.makeText(context, "Feature under development", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send Money")
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "\uD83D\uDD17 Generate Payment Link",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = linkAmount,
                onValueChange = { linkAmount = it },
                label = { Text("Amount (AFN)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Optional Note") },
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Button(
                onClick = {
                    if (linkAmount.isNotBlank()) {
                        paymentLink = "https://neobank.af/pay?user=jawid123&amount=${linkAmount}&note=${note.replace(" ", "%20")}"
                        Toast.makeText(context, "Link generated!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please enter amount", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Link")
            }

            if (paymentLink.isNotBlank()) {
                Text(
                    text = "Link: $paymentLink",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(paymentLink))
                        Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Copy Link")
                }
            }
        }
    }
}
