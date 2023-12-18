package com.aib.tictactoe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.aib.tictactoe.TicTacToeApplication
import com.aib.tictactoe.model.table.TableSizeListener
import com.aib.tictactoe.repository.tableSize.TableSizeRepository

class TableViewModel(tableSizeRepository: TableSizeRepository) : ViewModel(), TableSizeListener {
    init{
        tableSizeRepository.addTableSizeListener(this)
    }
    private val _size = MutableLiveData<Int>()
    val size: LiveData<Int> = _size

    override fun onTableSizeUpdate(size: Int) {
        Log.d("transaction", "onTableSizeUpdate")
        _size.value = size
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return TableViewModel(
                    (application as TicTacToeApplication).repositories.tableSizeRepository
                ) as T
            }
        }
    }
}