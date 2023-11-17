package com.aib.tictactoe.model.gameCreator

import com.aib.tictactoe.container.configuration.Config
import com.aib.tictactoe.model.table.TableSizeListener

class GameCreator {
    private val newGameListeners = ArrayList<NewGameListener>()
    private val tableSizeListeners = ArrayList<TableSizeListener>()


    fun addNewGameListener(newGameListener: NewGameListener){
        newGameListeners.add(newGameListener)
    }
    fun removeNewGameListener(newGameListener: NewGameListener){
        newGameListeners.remove(newGameListener)
    }


    fun removeTableSizeListener(tableSizeListener: TableSizeListener) {
        tableSizeListeners.remove(tableSizeListener)
    }
    fun addTableSizeListener(tableSizeListener: TableSizeListener) {
        tableSizeListeners.add(tableSizeListener)
    }

    fun create(){
        notifyNewGameListeners()
        notifyTableSizeListeners(Config.tableSize.sideLength)
    }
    private fun notifyNewGameListeners(){
        for (newGameListener in newGameListeners){
            newGameListener.onNewGame()
        }
    }
    private fun notifyTableSizeListeners(size: Int){
        for (tableSizeListener in tableSizeListeners){
            tableSizeListener.onTableSizeUpdate(size)
        }
    }
}