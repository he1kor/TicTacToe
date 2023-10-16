package com.aib.tictactoe.listener

import com.aib.tictactoe.CellFragment

interface OnMoveListener {
    fun onMove(cell: CellFragment)
}