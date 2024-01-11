package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

typealias ShiftItem = LogbookItem

class Morning : ShiftItem(
    idCard = 0,
    text = R.string.shift_morning,
    isCardTask = false,
    drawable = R.drawable.shift_morning
)

class Afternoon : ShiftItem(
    idCard = 1,
    text = R.string.shift_afternoon,
    isCardTask = false,
    drawable = R.drawable.shift_afternoon
)

class Night : ShiftItem(
    idCard = 2,
    text = R.string.shift_night,
    isCardTask = false,
    drawable = R.drawable.shift_night
)

fun getAllShiftItems(): List<ShiftItem>{
    return listOf(
        Morning(),
        Afternoon(),
        Night()
    )
}
