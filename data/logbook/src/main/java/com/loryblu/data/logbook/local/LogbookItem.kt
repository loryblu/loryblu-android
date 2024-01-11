package com.loryblu.data.logbook.local

sealed class LogbookItem (
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val isCardTask: Boolean = true,
    val isDisabled: Boolean = false
)