package com.example.neobank

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.neobank.ui.components.LanguageSwitcher
import com.example.neobank.ui.screens.MainScreen
import com.example.neobank.ui.theme.NeoBankTheme
import com.example.neobank.util.LocaleHelper

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        val updatedContext = LocaleHelper.updateResources(
            newBase,
            LocaleHelper.getStoredLanguage(newBase)
        )
        super.attachBaseContext(updatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Optional logging for debug purposes
        Log.d("AppConfigInfo", "API URL: ${BuildConfig.API_BASE_URL}")
        Log.d("AppConfigInfo", "APP_NAME_SUFFIX: ${BuildConfig.APP_NAME_SUFFIX}")
        Log.d("AppConfigInfo", "NEOBANK_API_KEY: ${BuildConfig.NEOBANK_API_KEY}")
        Log.d("AppConfigInfo", "SOME_OTHER_SDK_KEY: ${BuildConfig.SOME_OTHER_SDK_KEY}")

        enableEdgeToEdge()

        setContent {
            NeoBankTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                            navigationIcon = {
                                LanguageSwitcher { selectedLang ->
                                    LocaleHelper.setLocale(this, selectedLang)
                                    recreate() // apply new locale
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
