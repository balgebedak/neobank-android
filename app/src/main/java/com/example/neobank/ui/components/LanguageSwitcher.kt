package com.example.neobank.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LanguageSwitcher(onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        IconButton(onClick = {
            expanded = true
            scope.launch {
                delay(5000)
                expanded = false
            }
        }) {
            Icon(Icons.Filled.Language, contentDescription = "Select Language")
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("English") }, onClick = {
                onLanguageSelected("en")
                expanded = false
            })
            DropdownMenuItem(text = { Text("Pashto") }, onClick = {
                onLanguageSelected("ps")
                expanded = false
            })
            DropdownMenuItem(text = { Text("Dari") }, onClick = {
                onLanguageSelected("fa")
                expanded = false
            })
        }
    }
}
