package com.aib.tictactoe.logic

import com.aib.tictactoe.listener.OnWinListener
import com.aib.tictactoe.listener.ScoreListener

class ScoreCounter : OnWinListener{
    private var noughtScore = 0
    private var crossesScore = 0
    private var scoreListener: ScoreListener? = null

    fun setScoreListener(scoreListener: ScoreListener){
        this.scoreListener = scoreListener
    }

    fun reset(){
        noughtScore = 0
        crossesScore = 0
    }

    override fun onNoughtWin() {
        noughtScore++
        notifyNoughtScoreListener()
    }

    override fun onCrossesWin() {
        crossesScore++
        notifyCrossesScoreListener()
    }

    override fun onDraw() {}

    fun notifyNoughtScoreListener(){
        if (scoreListener == null)
            return
        scoreListener!!.onNoughtScoreUpdate(noughtScore)
    }
    fun notifyCrossesScoreListener(){
        if (scoreListener == null)
            return
        scoreListener!!.onCrossesScoreUpdate(crossesScore)
    }
}