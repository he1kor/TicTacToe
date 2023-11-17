package com.aib.tictactoe.model.scoreCounter

import com.aib.tictactoe.model.data.EndingStatus
import com.aib.tictactoe.model.data.GameStatus
import com.aib.tictactoe.model.turnProcessor.GameStatusListener

class ScoreCounter : GameStatusListener {
    private var noughtScore = 0
    private var crossesScore = 0
    private val scoreListeners = ArrayList<ScoreListener>()

    fun addScoreListener(scoreListener: ScoreListener){
        scoreListeners.add(scoreListener)
    }

    fun removeScoreListener(scoreListener: ScoreListener){
        scoreListeners.remove(scoreListener)
    }

    fun reset(){
        noughtScore = 0
        crossesScore = 0
    }

    private fun processCrossesWin() {
        crossesScore++
        notifyCrossesScoreListener()
    }
    private fun processNoughtsWin() {
        noughtScore++
        notifyNoughtScoreListener()
    }


    private fun notifyCrossesScoreListener(){
        scoreListeners.forEach {
            scoreListener -> scoreListener.onCrossesScoreUpdate(crossesScore)
        }
    }
    private fun notifyNoughtScoreListener(){
        scoreListeners.forEach {
                scoreListener -> scoreListener.onNoughtScoreUpdate(noughtScore)
        }
    }

    override fun onGameStatusUpdate(gameStatus: GameStatus) {
        when (gameStatus.endingStatus){
            EndingStatus.CROSSES_WIN -> processCrossesWin()
            EndingStatus.NOUGHTS_WIN -> processNoughtsWin()
            else -> {}
        }
    }
}