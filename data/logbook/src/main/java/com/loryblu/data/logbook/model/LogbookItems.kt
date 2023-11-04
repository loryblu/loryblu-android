package com.loryblu.data.logbook.model

import com.loryblu.data.logbook.R

class Routine: Item(
    idCard = 0,
    text = R.string.lory_routine,
    isCardTask = false,
    drawable = R.drawable.lory_routine
)

class Student: Item(
    idCard = 1,
    text = R.string.lory_student,
    isCardTask = false,
    drawable = R.drawable.lory_student
)

fun getAllLogbookItems(): List<Item>{
    return listOf(
        Routine(),
        Student(),
    )
}