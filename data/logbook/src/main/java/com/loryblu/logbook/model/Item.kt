package com.loryblu.logbook.model

sealed class Item (
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val isEnabled: Boolean = true
)