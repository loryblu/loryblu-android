package com.example.loryblu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.loryblu.ui.theme.LoryBluTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoryBluTheme {
//                var name = remember { mutableStateOf("")}
//                TextField(
//                    value = name,
//                    onValueChange = { it ->
//                        checkString(it)
//                    }
//                )
            }
        }
    }
}

