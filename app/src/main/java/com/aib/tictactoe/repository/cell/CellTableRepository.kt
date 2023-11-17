package com.aib.tictactoe.repository.cell

import com.aib.tictactoe.model.cell.CellUpdateListener
import com.aib.tictactoe.model.table.Table

class CellTableRepository : CellRepository {
    lateinit var table: Table

    fun linkTable(table: Table){
        this.table = table
    }

    override fun removeCellUpdateListener(cellUpdateListener: CellUpdateListener, row: Int, col: Int) {
        table.cellMatrix[row][col].removeCellStateListener(cellUpdateListener)
    }

    override fun addCellUpdateListener(cellUpdateListener: CellUpdateListener, row: Int, col: Int) {
        table.cellMatrix[row][col].addCellStateListener(cellUpdateListener)
    }

    override fun onClick(row: Int, col: Int) {
        table.cellMatrix[row][col].use()
    }
}