package com.aib.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aib.tictactoe.R
import com.aib.tictactoe.databinding.TableBinding
import com.aib.tictactoe.viewmodel.TableViewModel
import androidx.fragment.app.viewModels

class TableFragment : Fragment() {
    private lateinit var tableBinding: TableBinding
    private lateinit var tableView: LinearLayout
    private val cellTable : ArrayList<ArrayList<CellFragment>> = ArrayList()
    private val tableViewModel: TableViewModel by viewModels { TableViewModel.Factory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        tableBinding = TableBinding.inflate(inflater, container, false)
        setSizeObserver()
        return tableBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableView = view as LinearLayout
    }

    private fun setSizeObserver(){
        tableViewModel.size.observe(viewLifecycleOwner) { data -> resize(data)
        }
    }
    private fun resize(size: Int){
        val fragmentTransaction = parentFragmentManager.beginTransaction()

        clear(fragmentTransaction)
        create(size, fragmentTransaction)

        fragmentTransaction.commit()
        tableView.invalidate()
        tableView.requestLayout()
    }
    private fun clear(fragmentTransaction: FragmentTransaction){

        for (cellRow in cellTable){
            for (cell in cellRow){
                fragmentTransaction.remove(cell)
            }
        }
        cellTable.clear()
    }

    private fun create(size: Int, fragmentTransaction: FragmentTransaction){

        tableView.removeAllViews()
        for (r in 0 until size){
            cellTable.add(ArrayList())
            val row = layoutInflater.inflate(R.layout.row, tableView, false) as LinearLayout
            val rowId = View.generateViewId()
            row.id = rowId
            tableView.addView(row)
            for (c in 0 until size){
                val cellFragment = CellFragment(r, c)
                fragmentTransaction.add(rowId, cellFragment)
                cellTable[r].add(cellFragment)
            }
        }

    }
}