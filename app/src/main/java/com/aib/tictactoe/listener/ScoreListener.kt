package com.aib.tictactoe.listener

interface ScoreListener {
    fun onCrossesScoreUpdate(score: Int)
    fun onNoughtScoreUpdate(score: Int)
}