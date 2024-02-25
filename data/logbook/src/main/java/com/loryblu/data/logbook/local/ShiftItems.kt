package com.loryblu.data.logbook.local

import androidx.compose.ui.graphics.Color
import com.loryblu.data.logbook.R

typealias ShiftItem = LogbookItem

class Morning : ShiftItem(
    idCard = 0,
    text = R.string.shift_morning,
    drawable = R.drawable.shift_morning,
    color = Color(0XFF97D8FE)
)

class Afternoon : ShiftItem(
    idCard = 1,
    text = R.string.shift_afternoon,
    drawable = R.drawable.shift_afternoon,
    color = Color(0XFF2491D0)
)

class Night : ShiftItem(
    idCard = 2,
    text = R.string.shift_night,
    drawable = R.drawable.shift_night,
    color = Color(0XFF004A98)
)

fun getAllShiftItems(): List<ShiftItem>{
    return listOf(
        Morning(),
        Afternoon(),
        Night()
    )
}
