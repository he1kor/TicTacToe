package com.aib.tictactoe.model.table

import com.aib.tictactoe.model.cell.Cell
import com.aib.tictactoe.model.turnProcessor.CellFillListener

interface Table : TableSizeListener, Cell.CellUseListener, CellFillListener{
    val cellMatrix : ArrayList<ArrayList<Cell>>
    val cellCount: Int
    val filledCells: Int

    fun addCellUseListener(cellUseListener: Cell.CellUseListener)
    fun addSizeListener(sizeListener: TableSizeListener)
    fun removeSizeListener(sizeListener: TableSizeListener)
    fun removeCellUseListener(cellUseListener: Cell.CellUseListener)
}