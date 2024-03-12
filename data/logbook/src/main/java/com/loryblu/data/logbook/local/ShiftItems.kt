package com.loryblu.data.logbook.local

import androidx.compose.ui.graphics.Color
import com.loryblu.data.logbook.R

sealed class ShiftItem(
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val color: Color,
) {
    data object Morning : ShiftItem(
        idCard = 0,
        text = R.string.shift_morning,
        drawable = R.drawable.shift_morning,
        color = Color(0XFF97D8FE)
    )

    data object Afternoon : ShiftItem(
        idCard = 1,
        text = R.string.shift_afternoon,
        drawable = R.drawable.shift_afternoon,
        color = Color(0XFF2491D0)
    )

    data object Night : ShiftItem(
        idCard = 2,
        text = R.string.shift_night,
        drawable = R.drawable.shift_night,
        color = Color(0XFF004A98)
    )

    companion object {
        fun getShiftItems(): List<ShiftItem> = listOf(
            Morning,
            Afternoon,
            Night
        )

        fun getShiftItem(name: String): ShiftItem = when (name) {
            "morning" -> Morning
            "afternoon" -> Afternoon
            "night" -> Night
            else -> Morning
        }

        fun getShiftItem(int: Int) : ShiftItem = when (int) {
            0 -> Morning
            1 -> Afternoon
            2 -> Night
            else -> Morning
        }
    }

}
