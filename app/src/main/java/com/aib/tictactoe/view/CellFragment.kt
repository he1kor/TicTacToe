package com.aib.tictactoe.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aib.tictactoe.R
import com.aib.tictactoe.databinding.CellBinding
import com.aib.tictactoe.model.data.Chip
import com.aib.tictactoe.viewmodel.CellViewModel


class CellFragment(row: Int, col: Int) : Fragment(), View.OnClickListener {

    private val cellViewModel: CellViewModel by viewModels {CellViewModel.getFactory(row, col)}

    private lateinit var cellBinding: CellBinding

    override fun onClick(p0: View?) {
        cellViewModel.onClick()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cellBinding = CellBinding.inflate(inflater, container, false)
        cellBinding.root.setOnClickListener(this)
        setStateObserver()
        setIsVictoriousObserver()
        return cellBinding.root
    }

    private fun setStateObserver(){
        cellViewModel.state.observe(viewLifecycleOwner) {
                state -> updateState(state)
        }
    }
    private fun setIsVictoriousObserver(){
        cellViewModel.isVictorious.observe(viewLifecycleOwner) {
                isVictorious -> updateIsVictorious(isVictorious)
        }
    }


    private fun updateState(state: Chip){
        if (!::cellBinding.isInitialized)
            return
        val imageCell: ImageView = cellBinding.imageCell
        when (state){
            Chip.EMPTY -> imageCell.setImageResource(android.R.color.transparent)
            Chip.CROSS -> imageCell.setImageResource(R.drawable.cross)
            Chip.NOUGHT -> imageCell.setImageResource(R.drawable.nought)
        }
    }
    private fun updateIsVictorious(isVictorious: Boolean){
        if (!::cellBinding.isInitialized)
            return
        val backgroundCell: LinearLayout = cellBinding.background
        when (isVictorious){
            true -> backgroundCell.setBackgroundResource(R.drawable.cell_victorious)
            false -> backgroundCell.setBackgroundResource(R.drawable.cell_common)
        }
    }
}