package com.aib.tictactoe.view.gamePart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aib.tictactoe.databinding.NewGameButtonBinding
import com.aib.tictactoe.viewmodel.NewGameButtonViewModel

class NewGameButtonFragment : Fragment(), View.OnClickListener {

    private val newGameButtonViewModel: NewGameButtonViewModel by viewModels { NewGameButtonViewModel.Factory}

    private lateinit var newGameButtonBinding: NewGameButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newGameButtonBinding = NewGameButtonBinding.inflate(inflater, container, false)
        newGameButtonBinding.root.setOnClickListener(this)
        return newGameButtonBinding.root
    }

    override fun onClick(p0: View?) {
        newGameButtonViewModel.onClick()
    }
}