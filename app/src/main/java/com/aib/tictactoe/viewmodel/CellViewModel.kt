package com.aib.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.aib.tictactoe.TicTacToeApplication
import com.aib.tictactoe.model.cell.Cell
import com.aib.tictactoe.model.cell.CellUpdateListener
import com.aib.tictactoe.model.data.Chip
import com.aib.tictactoe.repository.cell.CellRepository

class CellViewModel(_cellRepository: CellRepository, _row: Int, _col: Int) : ViewModel(),
    CellUpdateListener {

    private val cellRepository = _cellRepository

    private val row = _row
    private val col = _col

    private val _isVictorious = MutableLiveData(false)
    val isVictorious: LiveData<Boolean> = _isVictorious

    private val _state = MutableLiveData(Chip.EMPTY)
    val state: LiveData<Chip> = _state

    init{
        cellRepository.addCellUpdateListener(this, row, col)
    }

    override fun onStateUpdate(state: Chip) {
        _state.value = state
    }

    override fun onVictoriousUpdate(isVictorious: Boolean) {
        _isVictorious.value = isVictorious
    }

    fun onClick(){
        cellRepository.onClick(row, col)
    }

    companion object {
        private class CellViewModelFactory(val row: Int, val col: Int) : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[
                        ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                )
                val cellRepository = (application as TicTacToeApplication).container.cellRepository

                return CellViewModel(cellRepository, row, col) as T
            }
        }

        fun getFactory(row: Int, col: Int): ViewModelProvider.Factory = CellViewModelFactory(row, col)
    }

}