package com.odisby.data.dashboard.local

import com.odisby.data.dashboard.R

class Logbook: DashboardItem(
    idCard = 0,
    text = R.string.logbook,
    drawable = R.drawable.logbook,
)

class StoryTrack: DashboardItem(
    idCard = 1,
    text = R.string.story_track,
    drawable = R.drawable.story_track,
    isDisabled = true
)

class GameTrack: DashboardItem(
    idCard = 2,
    text = R.string.game_track,
    drawable = R.drawable.game_track,
    isDisabled = true
)

fun getAllDashboardItems(): List<DashboardItem>{
    return listOf(
        Logbook(),
        StoryTrack(),
        GameTrack()
    )
}