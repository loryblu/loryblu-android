package com.loryblu.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.loryblu.loryblu.navigation.Screen
import com.loryblu.loryblu.navigation.SetupNavGraph
import com.loryblu.ui.theme.LoryBluTheme

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

