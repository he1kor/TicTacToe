package com.aib.tictactoe.model.scoreCounter

interface ScoreListener {
    fun onCrossesScoreUpdate(score: Int)
    fun onNoughtScoreUpdate(score: Int)
}