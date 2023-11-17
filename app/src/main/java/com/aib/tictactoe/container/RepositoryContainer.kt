package com.aib.tictactoe.container

import com.aib.tictactoe.repository.cell.CellRepository
import com.aib.tictactoe.repository.gameStatus.GameStatusRepository
import com.aib.tictactoe.repository.newGameButton.NewGameButtonRepository
import com.aib.tictactoe.repository.scoreCounter.ScoreCounterRepository
import com.aib.tictactoe.repository.tableSize.TableSizeRepository

interface RepositoryContainer {
    val tableSizeRepository: TableSizeRepository
    val cellRepository: CellRepository
    val gameStatusRepository: GameStatusRepository
    val newGameButtonRepository: NewGameButtonRepository
    val scoreCounterRepository: ScoreCounterRepository
}