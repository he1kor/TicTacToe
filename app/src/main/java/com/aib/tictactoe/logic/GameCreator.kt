package com.aib.tictactoe.logic

import com.aib.tictactoe.listener.NewGameListener

class GameCreator {
    private val newGameListeners = ArrayList<NewGameListener>()

    fun addNewGameListener(newGameListener: NewGameListener){
        newGameListeners.add(newGameListener)
    }
    fun removeNewGameListener(newGameListener: NewGameListener){
        newGameListeners.remove(newGameListener)
    }
    fun create(){
        for (newGameListener in newGameListeners){
            newGameListener.onNewGame()
        }
    }
}