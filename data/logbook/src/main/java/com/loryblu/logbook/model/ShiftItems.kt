package com.loryblu.logbook.model

import com.loryblu.logbook.R

class Morning : Item(
    idCard = 0,
    text = R.string.shift_morning,
    drawable = R.drawable.shift_morning
)

class Afternoon : Item(
    idCard = 1,
    text = R.string.shift_afternoon,
    drawable = R.drawable.shift_afternoon
)

class Night : Item(
    idCard = 2,
    text = R.string.shift_night,
    drawable = R.drawable.shift_night
)

fun getAllShiftItems(): List<Item>{
    return listOf(
        Morning(),
        Afternoon(),
        Night()
    )
}
