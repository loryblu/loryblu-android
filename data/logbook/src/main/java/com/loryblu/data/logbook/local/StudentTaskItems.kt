package com.loryblu.data.logbook.local

import com.loryblu.data.logbook.R

typealias StudentTaskItem = LogbookItem

class School : StudentTaskItem(
    idCard = 0,
    text = R.string.school,
    drawable = R.drawable.school_student
)

class SchoolReinforcement : StudentTaskItem(
    idCard = 1,
    text = R.string.school_reinforcement,
    drawable = R.drawable.reinforcementclass_student
)

class LanguageClass : StudentTaskItem(
    idCard = 2,
    text = R.string.language_class,
    drawable = R.drawable.language_student
)

class SportPractice : StudentTaskItem(
    idCard = 3,
    text = R.string.sport_practice,
    drawable = R.drawable.sportpractice_student
)

class ReadingTime : StudentTaskItem(
    idCard = 4,
    text = R.string.reading_time,
    drawable = R.drawable.readingtime_student
)

class MusicTherapy : StudentTaskItem(
    idCard = 5,
    text = R.string.music_therapy,
    drawable = R.drawable.musictherapy_student
)

class Psychologist : StudentTaskItem(
    idCard = 6,
    text = R.string.psychologist,
    drawable = R.drawable.psychologist_student
)

class Audiologist : StudentTaskItem(
    idCard = 7,
    text = R.string.audiologist,
    drawable = R.drawable.audiologist_student
)

class Pedagogue : StudentTaskItem(
    idCard = 8,
    text = R.string.pedagogue,
    drawable = R.drawable.pedagogue_student
)

class OccupationalTherapy : StudentTaskItem(
    idCard = 9,
    text = R.string.occupational_therapy,
    drawable = R.drawable.therapy_student
)

fun getAllStudentTaskItems(): List<StudentTaskItem>{
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
