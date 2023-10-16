package com.aib.tictactoe.listener

import com.aib.tictactoe.GameStatus

interface GameStatusListener {
    fun onGameStatusUpdate(gameStatus: GameStatus)
}