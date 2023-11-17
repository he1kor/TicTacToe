package com.aib.tictactoe.container

import com.aib.tictactoe.model.gameCreator.GameCreator
import com.aib.tictactoe.model.scoreCounter.ScoreCounter
import com.aib.tictactoe.model.table.DefaultTable
import com.aib.tictactoe.model.turnProcessor.TurnProcessor
import com.aib.tictactoe.model.winCheker.DefaultEndingChecker

class DefaultModelContainer : ModelContainer{
    override var table = DefaultTable()
    override var turnProcessor = TurnProcessor()
    override var endingChecker = DefaultEndingChecker()
    override var scoreCounter = ScoreCounter()
    override var gameCreator = GameCreator()
}