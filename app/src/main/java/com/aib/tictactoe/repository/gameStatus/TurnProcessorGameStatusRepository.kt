package com.aib.tictactoe.repository.gameStatus

import com.aib.tictactoe.model.turnProcessor.GameStatusListener
import com.aib.tictactoe.model.turnProcessor.TurnProcessor

class TurnProcessorGameStatusRepository : GameStatusRepository{
    private lateinit var turnProcessor: TurnProcessor

    fun linkTurnProcessor(turnProcessor: TurnProcessor){
        this.turnProcessor = turnProcessor
    }

    override fun addGameStatusListener(gameStatusListener: GameStatusListener) {
        turnProcessor.addGameStatusListener(gameStatusListener)
    }

    override fun removeGameStatusListener(gameStatusListener: GameStatusListener) {
        turnProcessor.removeGameStatusListener(gameStatusListener)
    }
}