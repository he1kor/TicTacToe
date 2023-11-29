package com.aib.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aib.tictactoe.databinding.NavigationBarBinding

class NavigationBarFragment : Fragment() {

    private lateinit var navigationBarBinding: NavigationBarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        navigationBarBinding = NavigationBarBinding.inflate(inflater, container, false)

        return navigationBarBinding.root
    }
}