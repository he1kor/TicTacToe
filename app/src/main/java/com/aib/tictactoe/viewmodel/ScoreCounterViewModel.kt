package com.aib.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.aib.tictactoe.TicTacToeApplication
import com.aib.tictactoe.model.scoreCounter.ScoreListener
import com.aib.tictactoe.repository.scoreCounter.ScoreCounterRepository

class ScoreCounterViewModel(scoreCounterRepository: ScoreCounterRepository) : ViewModel(), ScoreListener {

    private val _leftScoreText = MutableLiveData("0")
    val leftScoreText : LiveData<String> = _leftScoreText

    private val _rightScoreText = MutableLiveData("0")
    val rightScoreText : LiveData<String> = _rightScoreText

    init {
        scoreCounterRepository.addScoreListener(this)
    }

    override fun onCrossesScoreUpdate(score: Int) {
        _leftScoreText.value = score.toString()
    }

    override fun onNoughtScoreUpdate(score: Int) {
        _rightScoreText.value = score.toString()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return ScoreCounterViewModel(
                    (application as TicTacToeApplication).repositories.scoreCounterRepository
                ) as T
            }
        }
    }

}