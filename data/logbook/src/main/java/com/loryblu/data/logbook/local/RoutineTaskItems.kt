package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

typealias RoutineTaskItem = LogbookItem

class BathTime: RoutineTaskItem(
    idCard = 0,
    text = R.string.bath_time,
    drawable = R.drawable.bathtime_routine
)

class BrushTeeth : RoutineTaskItem(
    idCard = 1,
    text = R.string.brush_teeth,
    drawable = R.drawable.brushteeth_routine
)

class Breakfast : RoutineTaskItem(
    idCard = 2,
    text = R.string.breakfast,
    drawable = R.drawable.breakfast_routine
)

class Lunch : RoutineTaskItem(
    idCard = 3,
    text = R.string.lunch,
    drawable = R.drawable.lunch_routine
)

class SnackTime : RoutineTaskItem(
    idCard = 4,
    text = R.string.snack_time,
    drawable = R.drawable.snacktime_routine
)

class Dinner : RoutineTaskItem(
    idCard = 5,
    text = R.string.dinner,
    drawable = R.drawable.dinner_routine
)

class DrinkWater : RoutineTaskItem(
    idCard = 6,
    text = R.string.drink_water,
    drawable = R.drawable.drinkwater_routine
)

class PlayTime : RoutineTaskItem(
    idCard = 7,
    text = R.string.play_time,
    drawable = R.drawable.playtime_routine
)

class TVFree : RoutineTaskItem(
    idCard = 8,
    text = R.string.tv_free,
    drawable = R.drawable.tvfree_routine
)

class VideoGameFree : RoutineTaskItem(
    idCard = 9,
    text = R.string.video_game_free,
    drawable = R.drawable.videogame_routine
)

fun getAllRoutineItems(): List<RoutineTaskItem>{
    return listOf(
        BathTime(),
        BrushTeeth(),
        Breakfast(),
        Lunch(),
        SnackTime(),
        Dinner(),
        DrinkWater(),
        PlayTime(),
        TVFree(),
        VideoGameFree()
    )
}


