package com.aib.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.aib.tictactoe.databinding.CellBinding


class CellFragment(_row: Int, _column: Int) : Fragment(), View.OnClickListener {
    interface OnClickListener{
        fun onClick(cell: CellFragment)
    }
    interface CellFilledListener{
        fun onFilled(state: Chip)
    }
    private var cellFilledListener: CellFilledListener? = null
    private var onClickListener: OnClickListener? = null
    val row: Int = _row
    val column: Int = _column
    private lateinit var cellBinding: CellBinding
    var state: Chip = Chip.EMPTY
    set(value) {
        if (field == Chip.EMPTY && value != Chip.EMPTY)
            notifyCellFilledListener(value)
        field = value
        updateState()
    }
    var isVictorious = false
    set(value){
        field = value
        updateIsVictorious()
    }
    fun clear(){
        state = Chip.EMPTY
        isVictorious = false
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cellBinding = CellBinding.inflate(inflater, container, false)
        cellBinding.root.setOnClickListener(this)
        return cellBinding.root
    }

    private fun updateState(){
        if (!::cellBinding.isInitialized)
            return
        val imageCell: ImageView = cellBinding.imageCell
        when (state){
            Chip.EMPTY -> imageCell.setImageResource(android.R.color.transparent)
            Chip.CROSS -> imageCell.setImageResource(R.drawable.cross)
            Chip.NOUGHT -> imageCell.setImageResource(R.drawable.nought)
        }
    }
    private fun updateIsVictorious(){
        if (!::cellBinding.isInitialized)
            return
        val backgroundCell: LinearLayout = cellBinding.background
        when (isVictorious){
            true -> backgroundCell.setBackgroundResource(R.drawable.cell_victorious)
            false -> backgroundCell.setBackgroundResource(R.drawable.cell_common)
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }
    fun setCellFilledListener(cellFilledListener: CellFilledListener){
        this.cellFilledListener = cellFilledListener
    }
    override fun onClick(p0: View?) {if (onClickListener != null)
            onClickListener!!.onClick(this)
    }
    fun notifyCellFilledListener(state: Chip){
        if (cellFilledListener == null)
            return
        cellFilledListener!!.onFilled(state)
    }
}