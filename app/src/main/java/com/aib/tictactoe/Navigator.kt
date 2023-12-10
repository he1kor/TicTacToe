package com.aib.tictactoe

enum class ActivityPart{
    GAME,
    SETTINGS
}

interface Navigator {
    var lastActivityPart: ActivityPart
    fun navigate(activityPart: ActivityPart)
}