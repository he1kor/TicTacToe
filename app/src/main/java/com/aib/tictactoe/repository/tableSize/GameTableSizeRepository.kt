package com.aib.tictactoe.repository.tableSize

import com.aib.tictactoe.model.table.Table
import com.aib.tictactoe.model.table.TableSizeListener

class GameTableSizeRepository : TableSizeRepository{
    lateinit var table: Table

    fun linkTable(table: Table){
        this.table = table
    }

    override fun removeTableSizeListener(tableSizeListener: TableSizeListener) {
        table.addSizeListener(tableSizeListener)
    }

    override fun addTableSizeListener(tableSizeListener: TableSizeListener) {
        table.addSizeListener(tableSizeListener)
    }
}