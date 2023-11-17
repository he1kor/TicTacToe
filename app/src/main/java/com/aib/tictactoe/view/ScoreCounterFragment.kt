package com.aib.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aib.tictactoe.databinding.ScoreCounterBinding
import com.aib.tictactoe.viewmodel.ScoreCounterViewModel

class ScoreCounterFragment : Fragment() {

    private val scoreCounterViewModel: ScoreCounterViewModel by viewModels { ScoreCounterViewModel.Factory}

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
        setLeftScoreObserver()
        setRightScoreObserver()
        return binding.root
    }

    private fun setLeftScoreObserver(){
        scoreCounterViewModel.leftScoreText.observe(viewLifecycleOwner) {
            leftScore -> updateLeftScore(leftScore)
        }
    }
    private fun setRightScoreObserver(){
        scoreCounterViewModel.rightScoreText.observe(viewLifecycleOwner) {
                rightScore -> updateRightScore(rightScore)
        }
    }


    fun updateLeftScore(scoreText: String){
        leftTextView.text = scoreText
    }
    fun updateRightScore(scoreText: String){
        rightTextView.text = scoreText
    }
}