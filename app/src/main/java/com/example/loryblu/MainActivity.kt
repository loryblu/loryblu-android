package com.example.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.loryblu.createpassword.CreatePasswordScreen
import com.example.loryblu.createpassword.CreatePasswordViewModel
import com.example.loryblu.navigation.Screen
import com.example.loryblu.navigation.SetupNavGraph
import com.example.loryblu.ui.theme.LoryBluTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoryBluTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    startDestination = Screen.Login.route,
                    navController = navController
                )
            }
        }
    }
}

