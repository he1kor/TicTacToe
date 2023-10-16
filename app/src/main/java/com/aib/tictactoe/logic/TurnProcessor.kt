package com.aib.tictactoe.logic

import com.aib.tictactoe.CellFragment
import com.aib.tictactoe.Chip
import com.aib.tictactoe.GameStatus
import com.aib.tictactoe.listener.*

class TurnProcessor : CellFragment.OnClickListener, GameStatusListener, NewGameListener {

    var gameStatus = GameStatus.PLAY
        private set
    var currentTurn : Chip = Chip.CROSS
        private set

    private var onMoveListener: OnMoveListener? = null
    private var nextTurnListener: NextTurnListener? = null
    private var onWinListeners = ArrayList<OnWinListener>()

    fun setOnMoveListener(onMoveListener: OnMoveListener){
        this.onMoveListener = onMoveListener
    }
    fun setNextTurnListener(nextTurnListener: NextTurnListener){
        this.nextTurnListener = nextTurnListener
    }
    fun addOnWinListener(onWinListener: OnWinListener){
        onWinListeners.add(onWinListener)
    }
    fun removeOnWinListener(onWinListener: OnWinListener){
        onWinListeners.remove(onWinListener)
    }
    override fun onClick(cell: CellFragment) {
        if (gameStatus != GameStatus.PLAY)
            return
        if (cell.state != Chip.EMPTY)
            return
        cell.state = currentTurn
        notifyOnMoveListener(cell)
    }

    override fun onGameStatusUpdate(gameStatus: GameStatus) {
        this.gameStatus = gameStatus
        if (gameStatus == GameStatus.PLAY)
            nextTurn()
        else{
            notifyOnWinListener()
        }
    }
    fun clear(){
        gameStatus = GameStatus.PLAY
        currentTurn = Chip.CROSS
        notifyOnNextTurnListener()
    }

    private fun nextTurn(){
        currentTurn = if (currentTurn == Chip.NOUGHT)
            Chip.CROSS
        else
            Chip.NOUGHT
        notifyOnNextTurnListener()
    }
    private fun notifyOnMoveListener(cell: CellFragment){
        if (onMoveListener != null)
            onMoveListener!!.onMove(cell)
    }
    private fun notifyOnNextTurnListener(){
        if (nextTurnListener != null)
            nextTurnListener!!.onNextTurn(currentTurn)
    }
    private fun notifyOnWinListener() {
        for (onWinListener in onWinListeners) {
            when (gameStatus) {
                GameStatus.DRAW -> onWinListener.onDraw()
                GameStatus.CROSSES_WIN -> onWinListener.onCrossesWin()
                GameStatus.NOUGHTS_WIN -> onWinListener.onNoughtWin()
                else -> throw RuntimeException("Unknown winner")
            }
        }
    }
    override fun onNewGame() {
        clear()
    }
}