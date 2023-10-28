package com.loryblu.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.loryblu.core.network.di.Session
import com.loryblu.loryblu.navigation.SetupNavGraph
import com.loryblu.core.ui.theme.LoryBluTheme
import com.loryblu.core.util.Screen
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val session: Session by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoryBluTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    startDestination = if(session.getRememberLogin()) {
                        Screen.Home.route
                    } else {
                        Screen.Login.route
                    },
                    navController = navController
                )
            }
        }
    }
}

