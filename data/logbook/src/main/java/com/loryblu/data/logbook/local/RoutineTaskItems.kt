package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

sealed class RoutineTaskItem (
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val taskId: String
)

class BathTime: RoutineTaskItem(
    idCard = 0,
    text = R.string.bath_time,
    drawable = R.drawable.bathtime_routine,
    taskId = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a"
)

class BrushTeeth : RoutineTaskItem(
    idCard = 1,
    text = R.string.brush_teeth,
    drawable = R.drawable.brushteeth_routine,
    taskId = "61123e8a-22af-421f-8e0d-6356c920803b"
)

class Breakfast : RoutineTaskItem(
    idCard = 2,
    text = R.string.breakfast,
    drawable = R.drawable.breakfast_routine,
    taskId = "15d2ceb0-ab06-4cf6-8e46-54ccf8c1e556"
)

class Lunch : RoutineTaskItem(
    idCard = 3,
    text = R.string.lunch,
    drawable = R.drawable.lunch_routine,
    taskId = "5431e6bf-c1d3-43b6-9289-843ca6455ee3"
)

class SnackTime : RoutineTaskItem(
    idCard = 4,
    text = R.string.snack_time,
    drawable = R.drawable.snacktime_routine,
    taskId = "27e4089a-8643-487d-9634-2332419c2adc"
)

class Dinner : RoutineTaskItem(
    idCard = 5,
    text = R.string.dinner,
    drawable = R.drawable.dinner_routine,
    taskId = "a6284261-134d-4c96-ad1c-4afd50ae493d"
)

class DrinkWater : RoutineTaskItem(
    idCard = 6,
    text = R.string.drink_water,
    drawable = R.drawable.drinkwater_routine,
    taskId = "161bf998-4bf3-4c9f-bd72-1542bad40ed9"
)

class PlayTime : RoutineTaskItem(
    idCard = 7,
    text = R.string.play_time,
    drawable = R.drawable.playtime_routine,
    taskId = "86008ded-8ba1-4719-a55e-ef6d8d4cb8b1"
)

class TVFree : RoutineTaskItem(
    idCard = 8,
    text = R.string.tv_free,
    drawable = R.drawable.tvfree_routine,
    taskId = "bc920d36-d024-481b-9923-3d2d6a478a81"
)

class VideoGameFree : RoutineTaskItem(
    idCard = 9,
    text = R.string.video_game_free,
    drawable = R.drawable.videogame_routine,
    taskId = "63631065-7485-42d1-a9c1-523787954f56"
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


