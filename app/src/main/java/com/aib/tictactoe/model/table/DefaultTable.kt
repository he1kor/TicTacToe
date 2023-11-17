package com.aib.tictactoe.model.table

import com.aib.tictactoe.model.cell.Cell

class DefaultTable : Table{

    override val cellMatrix = ArrayList<ArrayList<Cell>>()

    override var cellCount = 0
        private set

    override var filledCells = 0
        private set

    private val sizeListeners = ArrayList<TableSizeListener>()
    private val cellUseListeners = ArrayList<Cell.CellUseListener>()

    fun resize(size: Int){
        clear()
        create(size)
    }

    fun clear(){
        cellMatrix.clear()
        filledCells = 0
        cellCount = 0
    }

    fun create(size: Int){
        calculateCellCount(size)
        for (r in 0 until size){
            cellMatrix.add(ArrayList())
            for (c in 0 until size){
                createCell(r, c)
            }
        }
        notifySizeListeners(size)
    }

    private fun createCell(row: Int, column: Int){
        cellMatrix[row].add(Cell(row, column))
        cellMatrix[row][column].addCellUseListener(this)
    }
    private fun calculateCellCount(sideSize: Int){
        cellCount = sideSize * sideSize
    }
    override fun addCellUseListener(cellUseListener: Cell.CellUseListener){
        cellUseListeners.add(cellUseListener)
    }
    override fun addSizeListener(sizeListener: TableSizeListener){
        sizeListeners.add(sizeListener)
    }
    override fun removeSizeListener(sizeListener: TableSizeListener){
        sizeListeners.remove(sizeListener)
    }
    override fun removeCellUseListener(cellUseListener: Cell.CellUseListener){
        cellUseListeners.remove(cellUseListener)
    }

    private fun notifySizeListeners(size: Int){
        sizeListeners.forEach { sizeListener ->
            sizeListener.onTableSizeUpdate(size)
        }
    }


    override fun onFilled(cell: Cell) {
        filledCells++
    }

    override fun onTableSizeUpdate(size: Int) {
        resize(size)
    }

    override fun onUse(cell: Cell) {
        cellUseListeners.forEach { cellUseListener ->
            cellUseListener.onUse(cell)
        }
    }
}