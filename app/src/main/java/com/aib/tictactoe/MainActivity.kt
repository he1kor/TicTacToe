package com.aib.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aib.tictactoe.databinding.ActivityMainBinding
import com.aib.tictactoe.logic.GameCreator
import com.aib.tictactoe.logic.ScoreCounter
import com.aib.tictactoe.logic.TurnProcessor
import com.aib.tictactoe.logic.WinChecker
import com.aib.tictactoe.view.NewGameButton
import com.aib.tictactoe.view.ScoreCounterFragment
import com.aib.tictactoe.view.TurnTextView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val table = Table(binding.table)
        table.create(this,5)

        val turnProcessor = TurnProcessor()
        val turnTextView = TurnTextView(binding.turnText)

        val winChecker = WinChecker(table, 4)

        val scoreCounter = ScoreCounter()
        val scoreCounterFragment = supportFragmentManager.findFragmentById(R.id.score_counter) as ScoreCounterFragment

        val gameCreator = GameCreator()
        val newGameButton = NewGameButton(binding.newGameButton, gameCreator)

        table.setOnButtonClickListener(turnProcessor)

        turnProcessor.setOnMoveListener(winChecker)
        turnProcessor.setNextTurnListener(turnTextView)
        turnProcessor.addOnWinListener(turnTextView)
        turnProcessor.addOnWinListener(scoreCounter)

        winChecker.setGameStatusListener(turnProcessor)

        scoreCounter.setScoreListener(scoreCounterFragment)

        gameCreator.addNewGameListener(table)
        gameCreator.addNewGameListener(turnProcessor)
        gameCreator.create()
    }
}