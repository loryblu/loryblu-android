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
    return days.map { dayOfWeekToInt(it) }
}

fun getNameOfDaySelected(days: List<Int>): List<String> {
    return days.map { intToDayOfWeek(it) }
}

fun dayOfWeekToInt(day: String): Int {
    return when (day) {
        "sun" -> {
            0
        }

        "mon" -> {
            1
        }

        "tue" -> {
            2
        }

        "wed" -> {
            3
        }

        "thu" -> {
            4
        }

        "fri" -> {
            5
        }

        "sat" -> {
            6
        }
        else -> {
            0
        }
    }
}

fun intToDayOfWeek(day: Int): String {
    return when (day) {
        0 -> "sun"
        1 -> "mon"
        2 -> "tue"
        3 -> "wed"
        4 -> "thu"
        5 -> "fri"
        6 -> "sat"
        else -> "sun"
    }
}
