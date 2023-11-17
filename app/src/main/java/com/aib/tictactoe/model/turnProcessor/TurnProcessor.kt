package com.aib.tictactoe.model.turnProcessor

import com.aib.tictactoe.model.data.Chip
import com.aib.tictactoe.model.data.EndingStatus
import com.aib.tictactoe.model.cell.Cell
import com.aib.tictactoe.model.data.GameStatus
import com.aib.tictactoe.model.winCheker.EndingStatusListener
import com.aib.tictactoe.model.gameCreator.NewGameListener

class TurnProcessor : Cell.CellUseListener, EndingStatusListener, NewGameListener {

    private val gameStatus = GameStatus(EndingStatus.PLAY, Chip.CROSS)

    private var endingChecker: EndingChecker? = null
    private val gameStatusListeners = ArrayList<GameStatusListener>()
    private val cellFillListeners = ArrayList<CellFillListener>()

    fun setEndingChecker(endingChecker: EndingChecker){
        this.endingChecker = endingChecker
        endingChecker.addGameStatusListener(this)
    }
    fun addGameStatusListener(gameStatusListener: GameStatusListener){
        gameStatusListeners.add(gameStatusListener)
    }
    fun removeGameStatusListener(gameStatusListener: GameStatusListener){
        gameStatusListeners.remove(gameStatusListener)
    }
    fun addCellFillListener(cellFillListener: CellFillListener){
        cellFillListeners.add(cellFillListener)
    }
    fun removeGameStatusListener(cellFillListener: CellFillListener){
        cellFillListeners.remove(cellFillListener)
    }

    /*
     *   Called when a cell has been used to process the turn.
     *   It notifies the endingChecker to check if the turn is ending.
     *   The next stage continues with the endGameStatus in onGameStatusUpdate
     */
    override fun onUse(cell: Cell) {
        if (gameStatus.endingStatus != EndingStatus.PLAY)
            return
        if (cell.state != Chip.EMPTY)
            return
        cell.state = gameStatus.turn

        notifyCellFillListeners(cell)
        endingChecker?.checkTurn(cell)
    }

    /*
     *   Called when receiving the endGameStatus to determine how this turn should be processed.
     */
    override fun onGameStatusChecked(endGameStatus: EndingStatus) {
        gameStatus.endingStatus = endGameStatus
        if (endGameStatus == EndingStatus.PLAY)
            nextTurn()
        notifyGameStatusListeners()
    }

    /*
     *   Called if the previous turn is not ending to switch the current turn to the next.
     */
    private fun nextTurn(){
        gameStatus.turn = if (gameStatus.turn == Chip.NOUGHT)
            Chip.CROSS
        else
            Chip.NOUGHT
    }

    fun clear(){
        gameStatus.endingStatus = EndingStatus.PLAY
        gameStatus.turn = Chip.CROSS
        notifyGameStatusListeners()
    }
    private fun notifyGameStatusListeners() {
        gameStatusListeners.forEach {
            gameStatusListener -> gameStatusListener.onGameStatusUpdate(gameStatus)
        }
    }
    private fun notifyCellFillListeners(cell: Cell) {
        cellFillListeners.forEach {
                cellFillListener -> cellFillListener.onFilled(cell)
        }
    }
    override fun onNewGame() {
        clear()
    }
}