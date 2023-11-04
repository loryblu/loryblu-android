package com.loryblu.logbook.model

import com.loryblu.logbook.R

class School : Item(
    idCard = 0,
    text = R.string.school,
    drawable = R.drawable.school_student
)

class SchoolReinforcement : Item(
    idCard = 1,
    text = R.string.school_reinforcement,
    drawable = R.drawable.reinforcementclass_student
)

class LanguageClass : Item(
    idCard = 2,
    text = R.string.language_class,
    drawable = R.drawable.language_student
)

class SportPractice : Item(
    idCard = 3,
    text = R.string.sport_practice,
    drawable = R.drawable.sportpractice_student
)

class ReadingTime : Item(
    idCard = 4,
    text = R.string.reading_time,
    drawable = R.drawable.readingtime_student
)

class MusicTherapy : Item(
    idCard = 5,
    text = R.string.music_therapy,
    drawable = R.drawable.musictherapy_student
)

class Psychologist : Item(
    idCard = 6,
    text = R.string.psychologist,
    drawable = R.drawable.psychologist_student
)

class Audiologist : Item(
    idCard = 7,
    text = R.string.audiologist,
    drawable = R.drawable.audiologist_student
)

class Pedagogue : Item(
    idCard = 8,
    text = R.string.pedagogue,
    drawable = R.drawable.pedagogue_student
)

class OccupationalTherapy : Item(
    idCard = 9,
    text = R.string.occupational_therapy,
    drawable = R.drawable.therapy_student
)

fun getAllStudentItems(): List<Item>{
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
