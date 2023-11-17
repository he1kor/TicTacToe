package com.aib.tictactoe.container

import com.aib.tictactoe.repository.cell.CellRepository
import com.aib.tictactoe.repository.cell.CellTableRepository
import com.aib.tictactoe.repository.gameStatus.GameStatusRepository
import com.aib.tictactoe.repository.gameStatus.TurnProcessorGameStatusRepository
import com.aib.tictactoe.repository.newGameButton.GameCreatorNewGameButtonRepository
import com.aib.tictactoe.repository.newGameButton.NewGameButtonRepository
import com.aib.tictactoe.repository.scoreCounter.DefaultScoreCounterRepository
import com.aib.tictactoe.repository.scoreCounter.ScoreCounterRepository
import com.aib.tictactoe.repository.tableSize.GameTableSizeRepository
import com.aib.tictactoe.repository.tableSize.TableSizeRepository

class DefaultRepositoryContainer : RepositoryContainer {
    override val tableSizeRepository: TableSizeRepository by lazy {
        GameTableSizeRepository()
    }
    override val cellRepository: CellRepository by lazy {
        CellTableRepository()
    }
    override val gameStatusRepository: GameStatusRepository by lazy{
        TurnProcessorGameStatusRepository()
    }
    override val newGameButtonRepository: NewGameButtonRepository by lazy{
        GameCreatorNewGameButtonRepository()
    }
    override val scoreCounterRepository: ScoreCounterRepository by lazy{
        DefaultScoreCounterRepository()
    }
}