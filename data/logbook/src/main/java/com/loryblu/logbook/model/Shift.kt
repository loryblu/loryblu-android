package com.loryblu.logbook.model

import com.loryblu.core.ui.R

data class Morning(
    override val idImage: Int = 0,
    override val imageText: Int = R.string.shift_morning,
    override val imageDrawable: Int = R.drawable.shift_morning
) : Item

data class Afternoon(
    override val idImage: Int = 1,
    override val imageText: Int = R.string.shift_afternoon,
    override val imageDrawable: Int = R.drawable.shift_afternoon
) : Item

data class Night(
    override val idImage: Int = 2,
    override val imageText: Int = R.string.shift_night,
    override val imageDrawable: Int = R.drawable.shift_night
) : Item
