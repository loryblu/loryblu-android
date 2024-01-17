package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

typealias CategoryItem = LogbookItem

class Routine: CategoryItem(
    idCard = 0,
    text = R.string.lory_routine,
    drawable = R.drawable.lory_routine
)

class Student: CategoryItem(
    idCard = 1,
    text = R.string.lory_student,
    drawable = R.drawable.lory_student
)

fun getAllCategoryItems(): List<CategoryItem>{
    return listOf(
        Routine(),
        Student(),
    )
}