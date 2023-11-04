package com.loryblu.data.logbook.model

sealed class Item (
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val isCardTask: Boolean = true,
    val isDisabled: Boolean = false
)