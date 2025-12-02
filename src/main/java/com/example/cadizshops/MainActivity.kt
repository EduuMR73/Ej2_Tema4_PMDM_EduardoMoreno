package com.example.cadizshops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cadizshops.presentation.navigation.AppNavigation
import com.example.cadizshops.presentation.ui.theme.CadizShopsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemTheme = isSystemInDarkTheme()
            val isDarkMode = rememberSaveable { mutableStateOf(systemTheme) }
            CadizShopsTheme(darkTheme = isDarkMode.value) {
                AppNavigation(isDarkMode = isDarkMode)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val isDarkMode = rememberSaveable { mutableStateOf(false) }
    CadizShopsTheme(darkTheme = isDarkMode.value) {
        AppNavigation(isDarkMode = isDarkMode)
    }
}
// por que al mapa no le afecta el modo oscuro

// sonido

