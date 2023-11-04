package com.loryblu.logbook.model

import com.loryblu.logbook.R

class Routine: Item(
    idCard = 0,
    text = R.string.lory_routine,
    drawable = R.drawable.lory_routine
)

class Student: Item(
    idCard = 1,
    text = R.string.lory_student,
    drawable = R.drawable.lory_student
)

fun getAllLogbookItems(): List<Item>{
    return listOf(
        Routine(),
        Student(),
    )
}