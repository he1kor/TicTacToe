package com.aib.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.aib.tictactoe.TicTacToeApplication
import com.aib.tictactoe.repository.newGameButton.NewGameButtonRepository

class NewGameButtonViewModel(_newGameButtonRepository: NewGameButtonRepository) : ViewModel(){

    private val newGameButtonRepository = _newGameButtonRepository

    fun onClick(){
        newGameButtonRepository.onClick()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return NewGameButtonViewModel(
                    (application as TicTacToeApplication).repositories.newGameButtonRepository
                ) as T
            }
        }
    }
}