package com.aib.tictactoe.repository.tableSize

import com.aib.tictactoe.model.table.TableSizeListener

interface TableSizeRepository {
    fun removeTableSizeListener(tableSizeListener: TableSizeListener)
    fun addTableSizeListener(tableSizeListener: TableSizeListener)
}