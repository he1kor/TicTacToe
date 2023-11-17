package com.aib.tictactoe.repository.scoreCounter

import com.aib.tictactoe.model.scoreCounter.ScoreListener

interface ScoreCounterRepository {
    fun addScoreListener(scoreListener: ScoreListener)
    fun removeScoreListener(scoreListener: ScoreListener)
}