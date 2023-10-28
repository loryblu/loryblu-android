package com.loryblu.logbook.model

import com.loryblu.core.ui.R

data class Routine(
    override val idImage: Int = 0,
    override val imageText: Int = R.string.lory_routine,
    override val imageDrawable: Int = R.drawable.lory_routine
) : Item

data class Student(
    override val idImage: Int = 1,
    override val imageText: Int = R.string.lory_student,
    override val imageDrawable: Int = R.drawable.lory_student
) : Item