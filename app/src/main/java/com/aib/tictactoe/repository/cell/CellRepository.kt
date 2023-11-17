package com.aib.tictactoe.repository.cell

import com.aib.tictactoe.model.cell.CellUpdateListener


interface CellRepository {

    fun removeCellUpdateListener(cellUpdateListener: CellUpdateListener, row: Int, col: Int)
    fun addCellUpdateListener(cellUpdateListener: CellUpdateListener, row: Int, col: Int)
    fun onClick(row: Int, col: Int)
}