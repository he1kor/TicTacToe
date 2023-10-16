package com.aib.tictactoe.view

import android.widget.TextView
import com.aib.tictactoe.Chip
import com.aib.tictactoe.R
import com.aib.tictactoe.listener.NextTurnListener
import com.aib.tictactoe.listener.OnWinListener

class TurnTextView(_textView: TextView) : NextTurnListener, OnWinListener {
    private val textView: TextView = _textView
    override fun onNextTurn(turn: Chip) {
        setTurnText(turn)
    }
    private fun setTurnText(turnChip: Chip){
        when (turnChip){
            Chip.CROSS -> textView.setText(R.string.crosses_turn_text)
            Chip.NOUGHT -> textView.setText(R.string.noughts_turn_text)
            else -> {throw RuntimeException("Unknown turn text")}
        }
    }
    private fun setNoughtsWin(){
        textView.setText(R.string.noughts_win)
    }
    private fun setCrossesWin(){
        textView.setText(R.string.crosses_win)
    }
    private fun setDraw(){
        textView.setText(R.string.draw)
    }

    override fun onNoughtWin() {
        setNoughtsWin()
    }

    override fun onCrossesWin() {
        setCrossesWin()
    }

    override fun onDraw() {
        setDraw()
    }
}