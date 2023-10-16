package com.aib.tictactoe.listener

import com.aib.tictactoe.Chip

interface NextTurnListener {
    fun onNextTurn(turn: Chip)
}