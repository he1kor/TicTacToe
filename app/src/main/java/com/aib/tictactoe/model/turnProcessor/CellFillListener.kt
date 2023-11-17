package com.aib.tictactoe.model.turnProcessor

import com.aib.tictactoe.model.cell.Cell

interface CellFillListener {
    fun onFilled(cell: Cell)
}