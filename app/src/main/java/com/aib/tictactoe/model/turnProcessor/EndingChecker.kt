package com.aib.tictactoe.model.turnProcessor

import com.aib.tictactoe.model.cell.Cell
import com.aib.tictactoe.model.winCheker.EndingStatusListener

interface EndingChecker {
    fun checkTurn(turnCell: Cell)
    fun addGameStatusListener(endingStatusListener: EndingStatusListener)
    fun removeGameStatusListener(endingStatusListener: EndingStatusListener)
}