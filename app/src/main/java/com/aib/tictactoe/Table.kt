package com.aib.tictactoe

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.aib.tictactoe.listener.NewGameListener

class Table(_table: LinearLayout) : NewGameListener, CellFragment.CellFilledListener{
    private val table = _table
    var cellCount: Int = 0
        private set
    var filledCells = 0
        private set
    var cellMatrix : ArrayList<ArrayList<CellFragment>> = ArrayList()
        private set

    fun create(appCompatActivity: AppCompatActivity, size: Int){
        val fragmentTransaction = appCompatActivity.supportFragmentManager.beginTransaction()
        cellMatrix.clear()
        table.removeAllViews()
        cellCount = size * size
        for (r in 0 until size){
            cellMatrix.add(ArrayList())
            val inflater = LayoutInflater.from(appCompatActivity)
            val row = inflater.inflate(R.layout.row, table, false) as LinearLayout
            val rowId = View.generateViewId()
            row.id = rowId
            table.addView(row)
            for (c in 0 until size){
                val cellFragment = CellFragment(r, c)
                fragmentTransaction.add(rowId, cellFragment)
                cellFragment.setCellFilledListener(this)
                cellMatrix[r].add(cellFragment)
            }
        }
        fragmentTransaction.commit()
        table.requestLayout()
    }

    fun setOnButtonClickListener(onClickListener: CellFragment.OnClickListener){
        for (row in cellMatrix){
            for (cellFragment in row){
                cellFragment.setOnClickListener(onClickListener)
            }
        }
    }
    fun getCellFragment(row: Int, col: Int): CellFragment {
        return cellMatrix[row][col]
    }
    fun clear(){
        filledCells = 0
        for (row in cellMatrix){
            for (cell in row){
                cell.clear()
            }
        }
    }
    override fun onNewGame() {
        clear()
    }

    override fun onFilled(state: Chip) {
        filledCells++
    }
}