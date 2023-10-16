package com.aib.tictactoe.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.aib.tictactoe.databinding.ScoreCounterBinding
import com.aib.tictactoe.listener.ScoreListener

class ScoreCounterFragment : Fragment(), ScoreListener{
    private lateinit var binding: ScoreCounterBinding
    private lateinit var leftTextView: TextView
    private lateinit var rightTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScoreCounterBinding.inflate(inflater, container, false)
        leftTextView = binding.scoreLeft
        rightTextView = binding.scoreRight
        return binding.root
    }

    override fun onCrossesScoreUpdate(score: Int) {
        updateLeftScore(score)
    }

    override fun onNoughtScoreUpdate(score: Int) {
        updateRightScore(score)
    }

    fun updateLeftScore(score: Int){
        leftTextView.setText(score.toString())
    }
    fun updateRightScore(score: Int){
        rightTextView.setText(score.toString())
    }
}