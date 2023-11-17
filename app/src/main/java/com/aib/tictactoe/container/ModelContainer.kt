package com.aib.tictactoe.container

import com.aib.tictactoe.model.gameCreator.GameCreator
import com.aib.tictactoe.model.scoreCounter.ScoreCounter
import com.aib.tictactoe.model.table.Table
import com.aib.tictactoe.model.turnProcessor.EndingChecker
import com.aib.tictactoe.model.turnProcessor.TurnProcessor

interface ModelContainer {
    val table: Table
    val turnProcessor: TurnProcessor
    val endingChecker: EndingChecker
    val scoreCounter: ScoreCounter
    val gameCreator: GameCreator
}