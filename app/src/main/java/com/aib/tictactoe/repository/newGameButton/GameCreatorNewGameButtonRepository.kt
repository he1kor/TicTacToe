package com.aib.tictactoe.repository.newGameButton

import com.aib.tictactoe.model.gameCreator.GameCreator

class GameCreatorNewGameButtonRepository : NewGameButtonRepository {

    private lateinit var gameCreator: GameCreator

    fun linkGameCreator(gameCreator: GameCreator){
        this.gameCreator = gameCreator
    }

    override fun onClick() {
        gameCreator.create()
    }
}