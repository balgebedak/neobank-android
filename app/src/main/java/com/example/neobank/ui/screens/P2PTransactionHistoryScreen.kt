package com.example.neobank.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Mock data class for transactions
data class P2PTransaction(
    val id: String,
    val type: String, // "Sent" or "Received"
    val user: String,
    val amount: Double,
    val currency: String,
    val status: String, // e.g., "Completed", "Pending", "Rejected"
    val timestamp: LocalDateTime,
    val note: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun P2PTransactionHistoryScreen(navController: NavController) {
    val allTransactions = remember {
        mutableStateListOf(
            P2PTransaction("1", "Sent", "@ahmad", 1000.0, "AFN", "Completed", LocalDateTime.now().minusDays(1), "Dinner"),
            P2PTransaction("2", "Received", "@zainab", 500.0, "AFN", "Pending", LocalDateTime.now().minusHours(3), "Rent"),
            P2PTransaction("3", "Sent", "@ali", 200.0, "AFN", "Rejected", LocalDateTime.now().minusDays(2))
        )
    }

    var filter by remember { mutableStateOf("All") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val formatter = DateTimeFormatter.ofPattern("MMM dd, HH:mm")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transactions") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                listOf("All", "Sent", "Received").forEach { category ->
                    FilterChip(
                        selected = filter == category,
                        onClick = { filter = category },
                        label = { Text(category) },
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val filtered = when (filter) {
                "Sent" -> allTransactions.filter { it.type == "Sent" }
                "Received" -> allTransactions.filter { it.type == "Received" }
                else -> allTransactions
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filtered) { tx ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Transaction with ${tx.user} on ${formatter.format(tx.timestamp)}")
                                }
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (tx.type == "Sent") Color(0xFFFFEBEE) else Color(0xFFE8F5E9)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "${tx.type} to ${tx.user}".takeIf { tx.type == "Sent" } ?: "${tx.type} from ${tx.user}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                tx.note?.let {
                                    Text(text = "\"$it\"", fontStyle = MaterialTheme.typography.labelSmall.fontStyle)
                                }
                                Text(
                                    text = formatter.format(tx.timestamp),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "${tx.currency} ${tx.amount}",
                                    fontWeight = FontWeight.Bold,
                                    color = if (tx.type == "Sent") Color.Red else Color(0xFF2E7D32)
                                )
                                Text(
                                    text = tx.status,
                                    color = when (tx.status) {
                                        "Pending" -> Color(0xFFFFA000)
                                        "Completed" -> Color(0xFF388E3C)
                                        "Rejected" -> Color(0xFFD32F2F)
                                        else -> MaterialTheme.colorScheme.onSurface
                                    },
                                    fontSize = 12.sp
                                )
                                if (tx.status == "Pending") {
                                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        TextButton(onClick = {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("Reminder sent to ${tx.user}")
                                            }
                                        }) {
                                            Text("Remind")
                                        }
                                        TextButton(onClick = {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("Canceled transaction to ${tx.user}")
                                            }
                                        }) {
                                            Text("Cancel")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
