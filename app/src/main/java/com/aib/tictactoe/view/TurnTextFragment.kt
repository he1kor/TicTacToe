package com.aib.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aib.tictactoe.databinding.TurnTextBinding
import com.aib.tictactoe.viewmodel.TurnTextViewModel

class TurnTextFragment : Fragment() {
    private lateinit var turnTextBinding: TurnTextBinding
    private lateinit var turnTextView: TextView
    private val turnTextViewModel: TurnTextViewModel by viewModels { TurnTextViewModel.Factory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        turnTextBinding = TurnTextBinding.inflate(inflater, container, false)
        setSizeObserver()

        return turnTextBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        turnTextView = view as TextView
    }

    private fun setSizeObserver(){
        turnTextViewModel.turnTextId.observe(viewLifecycleOwner) { data -> setTurnText(data)
        }
    }

    private fun setTurnText(turnTextId: Int){
        turnTextView.setText(turnTextId)
    }
}