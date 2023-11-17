package com.aib.tictactoe

import com.aib.tictactoe.container.RepositoryContainer
import com.aib.tictactoe.model.gameCreator.GameCreator
import com.aib.tictactoe.model.scoreCounter.ScoreCounter
import com.aib.tictactoe.model.turnProcessor.TurnProcessor
import com.aib.tictactoe.model.winCheker.DefaultEndingChecker
import com.aib.tictactoe.model.table.Table
import com.aib.tictactoe.model.turnProcessor.EndingChecker
import com.aib.tictactoe.repository.cell.CellTableRepository
import com.aib.tictactoe.repository.gameStatus.TurnProcessorGameStatusRepository
import com.aib.tictactoe.repository.newGameButton.GameCreatorNewGameButtonRepository
import com.aib.tictactoe.repository.scoreCounter.DefaultScoreCounterRepository
import com.aib.tictactoe.repository.tableSize.GameTableSizeRepository

class ModelInitializer(_container: RepositoryContainer) {
    val container = _container
    lateinit var table: Table
    lateinit var turnProcessor: TurnProcessor
    lateinit var endingChecker: EndingChecker
    lateinit var scoreCounter: ScoreCounter
    lateinit var gameCreator: GameCreator

    fun initialize(){
        table = Table()
        turnProcessor = TurnProcessor()
        scoreCounter = ScoreCounter()
        gameCreator = GameCreator()
        endingChecker = DefaultEndingChecker(table, 4)
    }

    fun link(){
        linkRepositories()
        linkModels()
    }

    private fun linkRepositories(){
        (container.tableSizeRepository as GameTableSizeRepository).linkTable(table)
        (container.cellRepository as CellTableRepository).linkTable(table)
        (container.gameStatusRepository as TurnProcessorGameStatusRepository).linkTurnProcessor(turnProcessor)
        (container.newGameButtonRepository as GameCreatorNewGameButtonRepository).linkGameCreator(gameCreator)
        (container.scoreCounterRepository as DefaultScoreCounterRepository).linkScoreCounter(scoreCounter)
    }

    private fun linkModels(){
        table.addCellUseListener(turnProcessor)

        turnProcessor.setEndingChecker(endingChecker)
        turnProcessor.addGameStatusListener(scoreCounter)
        turnProcessor.addCellFillListener(table)

        gameCreator.addTableSizeListener(table)
        gameCreator.addNewGameListener(turnProcessor)
    }
}