package com.aib.tictactoe.repository.scoreCounter

import com.aib.tictactoe.model.scoreCounter.ScoreCounter
import com.aib.tictactoe.model.scoreCounter.ScoreListener

class DefaultScoreCounterRepository : ScoreCounterRepository{

    private lateinit var scoreCounter: ScoreCounter

    fun linkScoreCounter(scoreCounter: ScoreCounter){
        this.scoreCounter = scoreCounter
    }

    override fun addScoreListener(scoreListener: ScoreListener) {
        scoreCounter.addScoreListener(scoreListener)
    }

    override fun removeScoreListener(scoreListener: ScoreListener) {
        scoreCounter.removeScoreListener(scoreListener)
    }
}