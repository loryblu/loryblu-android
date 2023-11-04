package com.loryblu.data.logbook.model

import com.loryblu.data.logbook.R

class LogbookHome: Item(
    idCard = 0,
    text = R.string.logbook_home,
    isCardTask = false,
    drawable = R.drawable.home_logbook,
)

class StoryTrackHome: Item(
    idCard = 1,
    text = R.string.story_track_home,
    isCardTask = false,
    drawable = R.drawable.home_story_track,
    isDisabled = true
)

class GameTrackHome: Item(
    idCard = 2,
    text = R.string.game_track_home,
    isCardTask = false,
    drawable = R.drawable.home_game_track,
    isDisabled = true
)

fun getAllHomeItems(): List<Item>{
    return listOf(
        LogbookHome(),
        StoryTrackHome(),
        GameTrackHome()
    )
}