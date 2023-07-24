package com.example.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.loryblu.login.LoginScreen
import com.example.loryblu.login.LoginViewModel
import com.example.loryblu.ui.theme.LoryBluTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoryBluTheme {
//                RegisterScreen(viewModel = RegisterViewModel())
                LoginScreen(viewModel = LoginViewModel())
            }
        }
    }
}

