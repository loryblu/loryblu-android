package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

class School : ItemOfCategory(
    idCard = 0,
    text = R.string.school,
    drawable = R.drawable.school_student,
    taskId = "44f29121-b7b1-4d1a-bbff-5f1cf2fc5497"
)

class SchoolReinforcement : ItemOfCategory(
    idCard = 1,
    text = R.string.school_reinforcement,
    drawable = R.drawable.reinforcementclass_student,
    taskId = "e9f17f88-7c94-4953-a1eb-bce035c483d8"
)

class LanguageClass : ItemOfCategory(
    idCard = 2,
    text = R.string.language_class,
    drawable = R.drawable.language_student,
    taskId = "8c004aa0-961d-4ec4-b6fe-cb70cbc0c4c1"
)

class SportPractice : ItemOfCategory(
    idCard = 3,
    text = R.string.sport_practice,
    drawable = R.drawable.sportpractice_student,
    taskId = "648d5ce9-6880-476e-bf98-0d7139aadb2c"
)

class ReadingTime : ItemOfCategory(
    idCard = 4,
    text = R.string.reading_time,
    drawable = R.drawable.readingtime_student,
    taskId = "549a70f6-3f9c-4640-9b1e-b23d69f1d213"
)

class MusicTherapy : ItemOfCategory(
    idCard = 5,
    text = R.string.music_therapy,
    drawable = R.drawable.musictherapy_student,
    taskId = "6166d506-b5f0-4feb-b900-e78d23f1e6ac"
)

class Psychologist : ItemOfCategory(
    idCard = 6,
    text = R.string.psychologist,
    drawable = R.drawable.psychologist_student,
    taskId = "49f9aed1-e583-427c-82bf-a596513a6707"
)

class Audiologist : ItemOfCategory(
    idCard = 7,
    text = R.string.audiologist,
    drawable = R.drawable.audiologist_student,
    taskId = "aa61d985-93a9-4ee0-b465-2e73848c84a8"
)

class Pedagogue : ItemOfCategory(
    idCard = 8,
    text = R.string.pedagogue,
    drawable = R.drawable.pedagogue_student,
    taskId = "1b2f2a80-9b3e-455d-aab4-66ba68e6dac0"
)

class OccupationalTherapy : ItemOfCategory(
    idCard = 9,
    text = R.string.occupational_therapy,
    drawable = R.drawable.therapy_student,
    taskId = "7da68252-143d-4e98-b5b0-17c7ea9953a3"
)

fun getAllStudentTaskItems(): List<ItemOfCategory>{
    return listOf(
        School(),
        SchoolReinforcement(),
        LanguageClass(),
        SportPractice(),
        ReadingTime(),
        MusicTherapy(),
        Psychologist(),
        Audiologist(),
        Pedagogue(),
        OccupationalTherapy()
    )
}
