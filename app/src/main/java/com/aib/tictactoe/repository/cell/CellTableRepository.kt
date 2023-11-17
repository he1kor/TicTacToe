package com.aib.tictactoe.repository.cell

import com.aib.tictactoe.model.table.Table
import com.aib.tictactoe.model.cell.CellUpdateListener

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