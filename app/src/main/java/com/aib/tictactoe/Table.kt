package com.aib.tictactoe

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class Table(_appCompatActivity: AppCompatActivity, _table: LinearLayout) : CellFragment.OnClickListener {
    private val appCompatActivity = _appCompatActivity
    private val table = _table
    private var cellMatrix : ArrayList<ArrayList<CellFragment>> = ArrayList()

    fun resizeMatrix(n: Int){
        val fragmentTransaction = appCompatActivity.supportFragmentManager.beginTransaction()
        cellMatrix.clear()
        table.removeAllViews()
        for (r in 0 until n){
            cellMatrix.add(ArrayList())
            val inflater = LayoutInflater.from(appCompatActivity)
            val row = inflater.inflate(R.layout.row, table, false) as LinearLayout
            val rowId = View.generateViewId()
            row.id = rowId
            table.addView(row)
            for (c in 0 until n){
                val cellFragment = CellFragment(this)
                fragmentTransaction.add(rowId, cellFragment)
                cellMatrix[r].add(cellFragment)
            }
        }
        fragmentTransaction.commit()
        table.requestLayout()
    }

    override fun onClick(cell: CellFragment) {
        when (cell.state){
            Chip.EMPTY -> cell.state = Chip.CROSS
            Chip.CROSS -> cell.state = Chip.NOUGHT
            Chip.NOUGHT -> cell.state = Chip.EMPTY
        }
    }
}