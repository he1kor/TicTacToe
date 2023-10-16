package com.aib.tictactoe.logic

import com.aib.tictactoe.*
import com.aib.tictactoe.listener.GameStatusListener
import com.aib.tictactoe.listener.OnMoveListener

class WinChecker(_table: Table, _winCellsAmount: Int) : OnMoveListener {
    private val directions = arrayOf(
        Direction(0, 1),
        Direction(1, 0),
        Direction(1, 1),
        Direction(1, -1)
    )
    private val table = _table
    var winCellsAmount = _winCellsAmount
    private var gameStatusListener : GameStatusListener? = null

    fun setGameStatusListener(gameStatusListener: GameStatusListener){
        this.gameStatusListener = gameStatusListener
    }
    override fun onMove(cell: CellFragment) {
        checkForWinCellsFrom(cell)
    }
    private fun checkForWinCellsFrom(startCell: CellFragment ){
        val winCells = ArrayList<CellFragment>()
        for (direction in directions){
            val sequentialCells = ArrayList<CellFragment>()
            sequentialCells.add(startCell)
            sequentialCells.addAll(getSequentialCells(startCell, direction))
            val reversedDirection = direction.newReverse()
            sequentialCells.addAll(getSequentialCells(startCell, reversedDirection))

            if (sequentialCells.size >= winCellsAmount)
                winCells.addAll(sequentialCells)
        }
        determineGameStatus(winCells)
        setCellVictorious(winCells)
    }
    private fun setCellVictorious(winCells: ArrayList<CellFragment>){
        for (cellFragment in winCells){
            cellFragment.isVictorious = true
        }
    }
    private fun determineGameStatus(winCells: ArrayList<CellFragment>){
        if (winCells.size == 0) {
            if (table.filledCells == table.cellCount)
                notifyGameStatusListener(GameStatus.DRAW)
            else
                notifyGameStatusListener(GameStatus.PLAY)
            return
        }
        when (winCells[0].state) {
            Chip.CROSS -> notifyGameStatusListener(GameStatus.CROSSES_WIN)
            Chip.NOUGHT -> notifyGameStatusListener(GameStatus.NOUGHTS_WIN)
            else -> throw RuntimeException("Unknown winner!")
        }
    }

    private fun getSequentialCells(startCell: CellFragment, direction: Direction) : ArrayList<CellFragment>{
        var currentCell = startCell
        val sequentialCells = ArrayList<CellFragment>()
        while (isOffsetCellExist(currentCell, direction)){
            currentCell = getOffsetCell(currentCell, direction)
            if (currentCell.state != startCell.state)
                break
            sequentialCells.add(currentCell)
        }
        return sequentialCells
    }
    private fun isOffsetCellExist(cell: CellFragment, direction: Direction): Boolean{
        if (cell.row + direction.row >= table.cellMatrix.size ||
            cell.row + direction.row < 0)
            return false
        if (cell.column + direction.col >= table.cellMatrix.size ||
            cell.column + direction.col < 0)
            return false
        return true
    }
    private fun getOffsetCell(cell: CellFragment, direction: Direction): CellFragment{
        return table.getCellFragment(cell.row + direction.row, cell.column + direction.col)
    }

    private fun notifyGameStatusListener(gameStatus: GameStatus){
        if (gameStatusListener != null)
            gameStatusListener!!.onGameStatusUpdate(gameStatus)
    }
}