package com.loryblu.logbook.model

import com.loryblu.logbook.R

class LogbookHome: Item(
    idCard = 0,
    text = R.string.logbook_home,
    drawable = R.drawable.home_logbook,
)

class StoryTrackHome: Item(
    idCard = 1,
    text = R.string.story_track_home,
    drawable = R.drawable.home_story_track,
    isEnabled = false
)

class GameTrackHome: Item(
    idCard = 2,
    text = R.string.game_track_home,
    drawable = R.drawable.home_game_track,
    isEnabled = false
)

fun getAllHomeItems(): List<Item>{
    return listOf(
        LogbookHome(),
        StoryTrackHome(),
        GameTrackHome()
    )
}