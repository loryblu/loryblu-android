package com.loryblu.feature.logbook.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EditTaskScreen() {
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Text(text = "Edit screen", color = Color.Black)
    }
}
