package com.example.neobank.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

// 🔹 Fake data model
data class Transaction(
    val title: String,
    val date: String,
    val amount: Double
)

// 🔹 Sample transactions
val sampleTransactions = listOf(
    Transaction("Sent to Ali", "2025-05-24", -500.0),
    Transaction("Received from Sara", "2025-05-23", 1500.0),
    Transaction("Bill Payment", "2025-05-22", -200.0),
    Transaction("Top-up", "2025-05-21", 1000.0)
)

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔹 Wallet Balance
        Text(
            text = "Wallet Balance",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "AFN 4,800.00", // 💰 mock balance
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // 🔹 Recent Transactions
        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sampleTransactions) { txn ->
                TransactionItem(txn)
            }
        }
    }
}

@Composable
fun TransactionItem(txn: Transaction) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = txn.title, fontWeight = FontWeight.Medium)
                Text(text = txn.date, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = "${if (txn.amount < 0) "-" else "+"}AFN ${kotlin.math.abs(txn.amount)}",
                color = if (txn.amount < 0) Color.Red else Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold
            )
        }
        Divider()
    }
}
