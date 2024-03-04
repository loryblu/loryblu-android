package com.loryblu.feature.logbook.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

fun shiftToInt(shift: String): Int {
    return when (shift) {
        "morning" -> {
            0
        }

        "afternoon" -> {
            1
        }

        else -> {
            2
        }
    }
}

fun intToShiftString(shift: Int): String {
    return when (shift) {
        0 -> "morning"
        1 -> "afternoon"
        else -> "night"
    }
}

fun getDaySelected(days: List<String>): List<Int> {
    val selectedDays = mutableListOf<Int>()
    val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")
    nameOfWeekDays.forEachIndexed { index, day ->
        if (days.any { it.contains(day) }) {
            selectedDays.add(index)
        }
    }
    return selectedDays
}

fun getNameOfDaySelected(days: List<Int>) : List<String> {
    val selectedDays = mutableListOf<String>()
    val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")
    nameOfWeekDays.forEachIndexed { index, day ->
        if (days.contains(index)) {
            selectedDays.add(day)
        }
    }
    return selectedDays
}