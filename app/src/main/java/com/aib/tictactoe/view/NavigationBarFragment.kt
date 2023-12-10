package com.aib.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aib.tictactoe.ActivityPart
import com.aib.tictactoe.Navigator
import com.aib.tictactoe.databinding.NavigationBarBinding

class NavigationBarFragment : Fragment() {

    private lateinit var binding: NavigationBarBinding

    private lateinit var gameButton: View
    private lateinit var settingsButton: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = NavigationBarBinding.inflate(inflater, container, false)

        gameButton = binding.gameButton
        settingsButton = binding.settingsButton

        setClickListener()

        return binding.root
    }

    private fun setClickListener(){
        setGameButtonOnClickListener()
        setSettingsButtonOnClickListener()
    }
    private fun setGameButtonOnClickListener(){
        gameButton.setOnClickListener{
            (activity as Navigator).navigate(ActivityPart.GAME)
        }
    }
    private fun setSettingsButtonOnClickListener(){
        settingsButton.setOnClickListener{
            (activity as Navigator).navigate(ActivityPart.SETTINGS)
        }
    }
}