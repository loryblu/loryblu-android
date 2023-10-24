package com.loryblu.logbook.model

import com.loryblu.core.ui.R

data class BathTime(
    override val idImage: Int = 0,
    override val imageText: Int = R.string.bath_time,
    override val imageDrawable: Int = R.drawable.bathtime_routine
) : Item

data class BrushTeeth(
    override val idImage: Int = 1,
    override val imageText: Int = R.string.brush_teeth,
    override val imageDrawable: Int = R.drawable.brushteeth_routine
) : Item

data class Breakfast(
    override val idImage: Int = 2,
    override val imageText: Int = R.string.breakfast,
    override val imageDrawable: Int = R.drawable.breakfast_routine
) : Item

data class Lunch(
    override val idImage: Int = 3,
    override val imageText: Int = R.string.lunch,
    override val imageDrawable: Int = R.drawable.lunch_routine
) : Item

data class SnackTime(
    override val idImage: Int = 4,
    override val imageText: Int = R.string.snack_time,
    override val imageDrawable: Int = R.drawable.snacktime_routine
) : Item

data class Dinner(
    override val idImage: Int = 5,
    override val imageText: Int = R.string.dinner,
    override val imageDrawable: Int = R.drawable.dinner_routine
) : Item

data class DrinkWater(
    override val idImage: Int = 6,
    override val imageText: Int = R.string.drink_water,
    override val imageDrawable: Int = R.drawable.drinkwater_routine
) : Item

data class PlayTime(
    override val idImage: Int = 7,
    override val imageText: Int = R.string.play_time,
    override val imageDrawable: Int = R.drawable.playtime_routine
) : Item

data class TVFree(
    override val idImage: Int = 8,
    override val imageText: Int = R.string.tv_free,
    override val imageDrawable: Int = R.drawable.tvfree_routine
) : Item

data class VideoGameFree(
    override val idImage: Int = 9,
    override val imageText: Int = R.string.video_game_free,
    override val imageDrawable: Int = R.drawable.videogame_routine
) : Item
