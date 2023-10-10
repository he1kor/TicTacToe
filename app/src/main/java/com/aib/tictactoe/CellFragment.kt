package com.aib.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.aib.tictactoe.databinding.CellBinding


class CellFragment(_onClickListener: OnClickListener) : Fragment(), View.OnClickListener {
    interface OnClickListener{
        fun onClick(cell: CellFragment)
    }
    private var onClickListener: OnClickListener = _onClickListener
    private lateinit var cellBinding: CellBinding
    var state: Chip = Chip.EMPTY
    set(value) {
        field = value
        update()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cellBinding = CellBinding.bind(view)
    }

    private fun update(){
        val imageCell: ImageView = cellBinding.imageCell
        when (state){
            Chip.EMPTY -> imageCell.setImageResource(android.R.color.transparent)
            Chip.CROSS -> imageCell.setImageResource(R.drawable.cross)
            Chip.NOUGHT -> imageCell.setImageResource(R.drawable.nought)
        }
    }

    override fun onClick(p0: View?) {
        onClickListener.onClick(this)
    }
}