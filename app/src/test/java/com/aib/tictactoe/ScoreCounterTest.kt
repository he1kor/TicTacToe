package com.aib.tictactoe

import com.aib.tictactoe.model.scoreCounter.ScoreListener
import com.aib.tictactoe.model.scoreCounter.ScoreCounter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ScoreCounterTest {

    private lateinit var scoreCounter: ScoreCounter
    private lateinit var scoreListener: ScoreListener

    @Before
    fun setUp() {
        scoreCounter = ScoreCounter()
        scoreListener = object : ScoreListener {
            override fun onNoughtScoreUpdate(score: Int) {
            }

            override fun onCrossesScoreUpdate(score: Int) {
            }
        }
        scoreCounter.setScoreListener(scoreListener)
    }

    @Test
    fun testOnNoughtWin() {
        scoreCounter.onNoughtWin()
        scoreCounter.onNoughtWin()
        scoreCounter.onNoughtWin()
        assertEquals(3, getNoughtScore())
    }

    @Test
    fun testOnCrossesWin() {
        scoreCounter.onCrossesWin()
        scoreCounter.onCrossesWin()
        scoreCounter.onCrossesWin()
        scoreCounter.onCrossesWin()
        assertEquals(4, getCrossesScore())
    }

    @Test
    fun testReset() {
        scoreCounter.onNoughtWin()
        scoreCounter.onCrossesWin()
        scoreCounter.reset()
        assertEquals(0, getNoughtScore())
        assertEquals(0, getCrossesScore())
    }

    private fun getNoughtScore(): Int {
        val noughtScoreField = ScoreCounter::class.java.getDeclaredField("noughtScore")
        noughtScoreField.isAccessible = true
        return noughtScoreField.get(scoreCounter) as Int
    }

    private fun getCrossesScore(): Int {
        val crossesScoreField = ScoreCounter::class.java.getDeclaredField("crossesScore")
        crossesScoreField.isAccessible = true
        return crossesScoreField.get(scoreCounter) as Int
    }
}