package com.example.neobank.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neobank.model.Transaction


@Composable
fun WalletScreen() {
    val recentTransactions = listOf(
        Transaction("Payment from Ahmad", 50.0, "May 24"),
        Transaction("Transfer to Zainab", -20.0, "May 23"),
        Transaction("Top-up", 100.0, "May 22")

    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Wallet Balance
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Wallet Balance", style = MaterialTheme.typography.labelLarge)
                Text(
                    "$1,230.00",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Recent Transactions
        Text("Recent Transactions", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

        LazyColumn {
            items(recentTransactions) { tx ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(tx.title, style = MaterialTheme.typography.bodyLarge)
                            Text(tx.date, style = MaterialTheme.typography.bodySmall)
                        }
                        Text(
                            text = "%.2f".format(tx.amount), // format amount to 2 decimal places
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (tx.amount >= 0) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.error
                        )

                    }
                }
            }
        }
    }
}
