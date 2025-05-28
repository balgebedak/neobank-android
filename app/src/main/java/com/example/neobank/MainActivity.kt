package com.example.neobank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.neobank.ui.navigation.AuthNavGraph
import com.example.neobank.ui.navigation.MainNavGraph
import com.example.neobank.ui.theme.NeoBankTheme
import com.example.neobank.ui.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

            NeoBankTheme {
                if (isAuthenticated) {
                    MainNavGraph(authViewModel)
                } else {
                    AuthNavGraph(authViewModel)
                }
            }
        }
    }
}
