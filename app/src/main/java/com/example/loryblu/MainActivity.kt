package com.example.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.loryblu.register.RegisterScreen
import com.example.loryblu.register.RegisterViewModel
import com.example.loryblu.ui.theme.LoryBluTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoryBluTheme {
                RegisterScreen(viewModel = RegisterViewModel())
            }
        }
    }
}

