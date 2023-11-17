package com.aib.tictactoe

import com.aib.tictactoe.container.ModelContainer
import com.aib.tictactoe.container.RepositoryContainer
import com.aib.tictactoe.model.winCheker.DefaultEndingChecker
import com.aib.tictactoe.model.table.DefaultTable
import com.aib.tictactoe.repository.cell.CellTableRepository
import com.aib.tictactoe.repository.gameStatus.TurnProcessorGameStatusRepository
import com.aib.tictactoe.repository.newGameButton.GameCreatorNewGameButtonRepository
import com.aib.tictactoe.repository.scoreCounter.DefaultScoreCounterRepository
import com.aib.tictactoe.repository.tableSize.GameTableSizeRepository

object Linker{

    fun link(repositories: RepositoryContainer, models: ModelContainer){
        linkRepositories(repositories, models)
        linkModels(models)
    }

    private fun linkRepositories(repositories: RepositoryContainer, models: ModelContainer){
        repositories.apply {
            (tableSizeRepository as GameTableSizeRepository).linkTable(models.table)
            (cellRepository as CellTableRepository).linkTable(models.table)
            (gameStatusRepository as TurnProcessorGameStatusRepository).linkTurnProcessor(models.turnProcessor)
            (newGameButtonRepository as GameCreatorNewGameButtonRepository).linkGameCreator(models.gameCreator)
            (scoreCounterRepository as DefaultScoreCounterRepository).linkScoreCounter(models.scoreCounter)
        }
    }

    private fun linkModels(models: ModelContainer){
        models.apply {
            (table as DefaultTable).addCellUseListener(turnProcessor)
            (endingChecker as DefaultEndingChecker).linkTable(models.table)

            turnProcessor.setEndingChecker(endingChecker)
            turnProcessor.addGameStatusListener(scoreCounter)
            turnProcessor.addCellFillListener(table)

            gameCreator.addTableSizeListener(table)
            gameCreator.addNewGameListener(turnProcessor)
        }
    }
}