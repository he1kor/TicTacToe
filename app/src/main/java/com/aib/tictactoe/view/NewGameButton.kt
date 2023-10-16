package com.aib.tictactoe.view

import android.view.View
import android.widget.Button
import com.aib.tictactoe.logic.GameCreator

class NewGameButton(_button: Button, _gameCreator: GameCreator) : View.OnClickListener {

    private val gameCreator = _gameCreator
    private val button = _button
    init{
        button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        gameCreator.create()
    }
}