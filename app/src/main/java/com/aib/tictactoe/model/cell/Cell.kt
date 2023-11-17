package com.aib.tictactoe.model.cell

import com.aib.tictactoe.model.data.Chip

class Cell(_row: Int, _column: Int) {

    interface CellUseListener{
        fun onUse(cell: Cell)
    }

    var row = _row
        private set
    var col = _column
        private set

    private val cellUpdateListeners = ArrayList<CellUpdateListener>()
    private val cellUseListeners = ArrayList<CellUseListener>()

    var isVictorious = false
        set(value){
            field = value
            notifyIsVictoriousListener()
        }

    var state: Chip = Chip.EMPTY
        set(value){
            field = value
            notifyStateListener()
        }

    fun clear(){
        state = Chip.EMPTY
        isVictorious = false
    }

    fun use(){
        cellUseListeners.forEach { cellUseListener ->
            cellUseListener.onUse(this)

        }
    }

    fun addCellStateListener(cellUpdateListener: CellUpdateListener){
        cellUpdateListeners.add(cellUpdateListener)
    }
    fun removeCellStateListener(cellUpdateListener: CellUpdateListener){
        cellUpdateListeners.remove(cellUpdateListener)
    }

    fun addCellUseListener(cellUseListener: CellUseListener){
        cellUseListeners.add(cellUseListener)
    }
    fun removeCellUseListener(cellUseListener: CellUseListener){
        cellUseListeners.remove(cellUseListener)
    }



    private fun notifyIsVictoriousListener() {
        cellUpdateListeners.forEach { cellUpdateListener ->
            cellUpdateListener.onVictoriousUpdate(isVictorious)
        }
    }
    private fun notifyStateListener(){
        cellUpdateListeners.forEach { cellUpdateListener ->
            cellUpdateListener.onStateUpdate(state)
        }
    }
}