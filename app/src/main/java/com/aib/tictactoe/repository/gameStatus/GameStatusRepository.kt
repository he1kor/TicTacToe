package com.aib.tictactoe.repository.gameStatus

import com.aib.tictactoe.model.turnProcessor.GameStatusListener

interface GameStatusRepository {
    fun addGameStatusListener(gameStatusListener: GameStatusListener)
    fun removeGameStatusListener(gameStatusListener: GameStatusListener)
}