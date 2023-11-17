package com.aib.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.aib.tictactoe.R
import com.aib.tictactoe.TicTacToeApplication
import com.aib.tictactoe.model.data.Chip
import com.aib.tictactoe.model.data.EndingStatus
import com.aib.tictactoe.model.data.GameStatus
import com.aib.tictactoe.model.turnProcessor.GameStatusListener
import com.aib.tictactoe.repository.gameStatus.GameStatusRepository

class TurnTextViewModel(gameStatusRepository: GameStatusRepository) : ViewModel(), GameStatusListener {

    private val _turnTextId = MutableLiveData<Int>()
    val turnTextId: LiveData<Int> = _turnTextId

    init{
        gameStatusRepository.addGameStatusListener(this)
    }

    override fun onGameStatusUpdate(gameStatus: GameStatus) {
        if (gameStatus.endingStatus == EndingStatus.PLAY) {
            _turnTextId.value =
                if (gameStatus.turn == Chip.CROSS)
                    R.string.crosses_turn_text
                else
                    R.string.noughts_turn_text
            return
        }
        _turnTextId.value = when (gameStatus.endingStatus){
            EndingStatus.NOUGHTS_WIN -> R.string.noughts_win
            EndingStatus.CROSSES_WIN -> R.string.crosses_win
            EndingStatus.DRAW -> R.string.draw
            else -> {throw RuntimeException("Unexpected ending status")}
        }

    }
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return TurnTextViewModel(
                    (application as TicTacToeApplication).container.gameStatusRepository
                ) as T
            }
        }
    }

}