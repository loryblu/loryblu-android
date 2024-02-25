package com.loryblu.data.logbook.local

import androidx.compose.ui.graphics.Color


sealed class LogbookItem (
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val isDisabled: Boolean = false,
    val color: Color = Color.Unspecified
)