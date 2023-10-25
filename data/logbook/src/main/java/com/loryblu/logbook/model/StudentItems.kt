package com.loryblu.logbook.model

import com.loryblu.core.ui.R

data class School(
    override val idImage: Int = 0,
    override val imageText: Int = R.string.school,
    override val imageDrawable: Int = R.drawable.school_student
) : Item

data class SchoolReinforcement(
    override val idImage: Int = 1,
    override val imageText: Int = R.string.school_reinforcement,
    override val imageDrawable: Int = R.drawable.reinforcementclass_student
) : Item

data class LanguageClass(
    override val idImage: Int = 2,
    override val imageText: Int = R.string.language_class,
    override val imageDrawable: Int = R.drawable.language_student
) : Item

data class SportPractice(
    override val idImage: Int = 3,
    override val imageText: Int = R.string.sport_practice,
    override val imageDrawable: Int = R.drawable.sportpractice_student
) : Item

data class ReadingTime(
    override val idImage: Int = 4,
    override val imageText: Int = R.string.reading_time,
    override val imageDrawable: Int = R.drawable.readingtime_student
) : Item

data class MusicTherapy(
    override val idImage: Int = 5,
    override val imageText: Int = R.string.music_therapy,
    override val imageDrawable: Int = R.drawable.musictherapy_student
) : Item

data class Psychologist(
    override val idImage: Int = 6,
    override val imageText: Int = R.string.psychologist,
    override val imageDrawable: Int = R.drawable.psychologist_student
) : Item

data class Audiologist(
    override val idImage: Int = 7,
    override val imageText: Int = R.string.audiologist,
    override val imageDrawable: Int = R.drawable.audiologist_student
) : Item

data class Pedagogue(
    override val idImage: Int = 8,
    override val imageText: Int = R.string.pedagogue,
    override val imageDrawable: Int = R.drawable.pedagogue_student
) : Item

data class OccupationalTherapy(
    override val idImage: Int = 9,
    override val imageText: Int = R.string.occupational_therapy,
    override val imageDrawable: Int = R.drawable.therapy_student
) : Item
