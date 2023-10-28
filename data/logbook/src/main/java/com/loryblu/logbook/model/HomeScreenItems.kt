package com.loryblu.logbook.model

import com.loryblu.core.ui.R

data class LogbookHome(
    override val idImage: Int = 0,
    override val imageText: Int = R.string.logbook_home,
    override val imageDrawable: Int = R.drawable.home_logbook
) : Item

data class StoryTrackHome(
    override val idImage: Int = 1,
    override val imageText: Int = R.string.story_track_home,
    override val imageDrawable: Int = R.drawable.home_story_track
) : Item

data class GameTrackHome(
    override val idImage: Int = 2,
    override val imageText: Int = R.string.game_track_home,
    override val imageDrawable: Int = R.drawable.home_game_track
) : Item