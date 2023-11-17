package com.aib.tictactoe.model.winCheker

import com.aib.tictactoe.model.turnProcessor.EndingChecker
import com.aib.tictactoe.model.cell.Cell
import com.aib.tictactoe.model.data.Chip
import com.aib.tictactoe.model.data.EndingStatus
import com.aib.tictactoe.model.data.Vector
import com.aib.tictactoe.model.table.Table

class DefaultEndingChecker(_table: Table, _winCellsAmount: Int) : EndingChecker {
    private val shiftVectors = arrayOf(
        Vector(0, 1),
        Vector(1, 0),
        Vector(1, 1),
        Vector(1, -1)
    )

    private val table = _table
    private var winCellsAmount = _winCellsAmount
    private val endingStatusListeners = ArrayList<EndingStatusListener>()

    override fun addGameStatusListener(endingStatusListener: EndingStatusListener){
        endingStatusListeners.add(endingStatusListener)
    }
    override fun removeGameStatusListener(endingStatusListener: EndingStatusListener){
        endingStatusListeners.remove(endingStatusListener)
    }
    override fun checkTurn(turnCell: Cell){
        val winCells = ArrayList<Cell>()
        for (shiftVector in shiftVectors){
            val sequentialCells = ArrayList<Cell>()
            sequentialCells.add(turnCell)

            sequentialCells.addAll(getSequentialCells(turnCell, shiftVector))
            shiftVector.reverse()
            sequentialCells.addAll(getSequentialCells(turnCell, shiftVector))

            if (sequentialCells.size >= winCellsAmount)
                winCells.addAll(sequentialCells)
        }
        determineGameStatus(winCells)
        setCellVictorious(winCells)
    }
    private fun setCellVictorious(winCells: ArrayList<Cell>){
        for (cellFragment in winCells){
            cellFragment.isVictorious = true
        }
    }
    private fun determineGameStatus(winCells: ArrayList<Cell>){
        notifyGameStatusListeners(
            if (winCells.size == 0)
                if (table.filledCells == table.cellCount)
                    EndingStatus.DRAW
                else
                    EndingStatus.PLAY
            else
                if (winCells[0].state == Chip.CROSS)
                    EndingStatus.CROSSES_WIN
                else
                    EndingStatus.NOUGHTS_WIN
        )
    }

    private fun getSequentialCells(startCell: Cell, shiftVector: Vector) : ArrayList<Cell>{
        var currentCell = startCell
        val sequentialCells = ArrayList<Cell>()
        while (isOffsetCellExist(currentCell, shiftVector)){
            currentCell = getOffsetCell(currentCell, shiftVector)
            if (currentCell.state != startCell.state)
                break
            sequentialCells.add(currentCell)
        }
        return sequentialCells
    }
    private fun isOffsetCellExist(cell: Cell, shiftVector: Vector): Boolean{
        if (cell.row + shiftVector.row >= table.cellMatrix.size ||
            cell.row + shiftVector.row < 0)
            return false
        if (cell.col + shiftVector.col >= table.cellMatrix.size ||
            cell.col + shiftVector.col < 0)
            return false
        return true
    }
    private fun getOffsetCell(cell: Cell, vector: Vector): Cell {
        return table.cellMatrix[cell.row + vector.row][cell.col + vector.col]
    }

    private fun notifyGameStatusListeners(endGameStatus: EndingStatus){
        endingStatusListeners.forEach {
            gameStatusListener -> gameStatusListener.onGameStatusChecked(endGameStatus)
        }
    }
}