package com.example.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.loryblu.createpassword.CreatePasswordScreen
import com.example.loryblu.createpassword.CreatePasswordViewModel
import com.example.loryblu.ui.theme.LoryBluTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoryBluTheme {
//                GuardianRegisterScreen(viewModel = GuardianRegisterViewModel())
//                LoginScreen(viewModel = LoginViewModel())
//                ForgotPasswordScreen(viewModel = ForgotPasswordViewModel())
                CreatePasswordScreen(viewModel = CreatePasswordViewModel())
//                LorybluApp()
            }
        }
    }
}

