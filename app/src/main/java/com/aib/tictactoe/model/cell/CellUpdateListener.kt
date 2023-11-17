package com.aib.tictactoe.model.cell

import com.aib.tictactoe.model.data.Chip

interface CellUpdateListener {
    fun onStateUpdate(state: Chip)
    fun onVictoriousUpdate(isVictorious: Boolean)
}