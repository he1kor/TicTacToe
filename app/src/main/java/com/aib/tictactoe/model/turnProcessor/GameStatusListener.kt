package com.aib.tictactoe.model.turnProcessor

import com.aib.tictactoe.model.data.GameStatus

interface GameStatusListener {
    fun onGameStatusUpdate(gameStatus: GameStatus)
}