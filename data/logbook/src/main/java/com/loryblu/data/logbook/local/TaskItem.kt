package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

sealed class TaskItem(
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val taskId: String,
    val category: CategoryItem
) {
    data object BathTime: TaskItem(
        idCard = 0,
        text = R.string.bath_time,
        drawable = R.drawable.bathtime_routine,
        taskId = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
        category = CategoryItem.getCategory(0)
    )

    data object BrushTeeth : TaskItem(
        idCard = 1,
        text = R.string.brush_teeth,
        drawable = R.drawable.brushteeth_routine,
        taskId = "61123e8a-22af-421f-8e0d-6356c920803b",
        category = CategoryItem.getCategory(0)
    )

    data object Breakfast : TaskItem(
        idCard = 2,
        text = R.string.breakfast,
        drawable = R.drawable.breakfast_routine,
        taskId = "15d2ceb0-ab06-4cf6-8e46-54ccf8c1e556",
        category = CategoryItem.getCategory(0)
    )

    data object Lunch : TaskItem(
        idCard = 3,
        text = R.string.lunch,
        drawable = R.drawable.lunch_routine,
        taskId = "5431e6bf-c1d3-43b6-9289-843ca6455ee3",
        category = CategoryItem.getCategory(0)
    )

    data object SnackTime : TaskItem(
        idCard = 4,
        text = R.string.snack_time,
        drawable = R.drawable.snacktime_routine,
        taskId = "27e4089a-8643-487d-9634-2332419c2adc",
        category = CategoryItem.getCategory(0)
    )

    data object Dinner : TaskItem(
        idCard = 5,
        text = R.string.dinner,
        drawable = R.drawable.dinner_routine,
        taskId = "a6284261-134d-4c96-ad1c-4afd50ae493d",
        category = CategoryItem.getCategory(0)
    )

    data object DrinkWater : TaskItem(
        idCard = 6,
        text = R.string.drink_water,
        drawable = R.drawable.drinkwater_routine,
        taskId = "161bf998-4bf3-4c9f-bd72-1542bad40ed9",
        category = CategoryItem.getCategory(0)
    )

    data object PlayTime : TaskItem(
        idCard = 7,
        text = R.string.play_time,
        drawable = R.drawable.playtime_routine,
        taskId = "86008ded-8ba1-4719-a55e-ef6d8d4cb8b1",
        category = CategoryItem.getCategory(0)
    )

    data object TVFree : TaskItem(
        idCard = 8,
        text = R.string.tv_free,
        drawable = R.drawable.tvfree_routine,
        taskId = "bc920d36-d024-481b-9923-3d2d6a478a81",
        category = CategoryItem.getCategory(0)
    )

    data object VideoGameFree : TaskItem(
        idCard = 9,
        text = R.string.video_game_free,
        drawable = R.drawable.videogame_routine,
        taskId = "63631065-7485-42d1-a9c1-523787954f56",
        category = CategoryItem.getCategory(0)
    )

    data object School : TaskItem(
        idCard = 0,
        text = R.string.school,
        drawable = R.drawable.school_student,
        taskId = "44f29121-b7b1-4d1a-bbff-5f1cf2fc5497",
        category = CategoryItem.getCategory(1)
    )

    data object SchoolReinforcement : TaskItem(
        idCard = 1,
        text = R.string.school_reinforcement,
        drawable = R.drawable.reinforcementclass_student,
        taskId = "e9f17f88-7c94-4953-a1eb-bce035c483d8",
        category = CategoryItem.getCategory(1)
    )

    data object LanguageClass : TaskItem(
        idCard = 2,
        text = R.string.language_class,
        drawable = R.drawable.language_student,
        taskId = "8c004aa0-961d-4ec4-b6fe-cb70cbc0c4c1",
        category = CategoryItem.getCategory(1)
    )

    data object SportPractice : TaskItem(
        idCard = 3,
        text = R.string.sport_practice,
        drawable = R.drawable.sportpractice_student,
        taskId = "648d5ce9-6880-476e-bf98-0d7139aadb2c",
        category = CategoryItem.getCategory(1)
    )

    data object ReadingTime : TaskItem(
        idCard = 4,
        text = R.string.reading_time,
        drawable = R.drawable.readingtime_student,
        taskId = "549a70f6-3f9c-4640-9b1e-b23d69f1d213",
        category = CategoryItem.getCategory(1)
    )

    data object MusicTherapy : TaskItem(
        idCard = 5,
        text = R.string.music_therapy,
        drawable = R.drawable.musictherapy_student,
        taskId = "6166d506-b5f0-4feb-b900-e78d23f1e6ac",
        category = CategoryItem.getCategory(1)
    )

    data object Psychologist : TaskItem(
        idCard = 6,
        text = R.string.psychologist,
        drawable = R.drawable.psychologist_student,
        taskId = "49f9aed1-e583-427c-82bf-a596513a6707",
        category = CategoryItem.getCategory(1)
    )

    data object Audiologist : TaskItem(
        idCard = 7,
        text = R.string.audiologist,
        drawable = R.drawable.audiologist_student,
        taskId = "aa61d985-93a9-4ee0-b465-2e73848c84a8",
        category = CategoryItem.getCategory(1)
    )

    data object Pedagogue : TaskItem(
        idCard = 8,
        text = R.string.pedagogue,
        drawable = R.drawable.pedagogue_student,
        taskId = "1b2f2a80-9b3e-455d-aab4-66ba68e6dac0",
        category = CategoryItem.getCategory(1)
    )

    data object OccupationalTherapy : TaskItem(
        idCard = 9,
        text = R.string.occupational_therapy,
        drawable = R.drawable.therapy_student,
        taskId = "7da68252-143d-4e98-b5b0-17c7ea9953a3",
        category = CategoryItem.getCategory(1)
    )

    companion object {
        fun getAllTaskItems(): List<TaskItem> {
            return listOf(
                BathTime,
                BrushTeeth,
                Breakfast,
                Lunch,
                SnackTime,
                Dinner,
                DrinkWater,
                PlayTime,
                TVFree,
                VideoGameFree,
                School,
                SchoolReinforcement,
                LanguageClass,
                SportPractice,
                ReadingTime,
                MusicTherapy,
                Psychologist,
                Audiologist,
                Pedagogue,
                OccupationalTherapy
            )
        }

        fun getTaskItem(taskId: String): TaskItem {
            return getAllTaskItems().first { it.taskId == taskId }
        }
    }
}
