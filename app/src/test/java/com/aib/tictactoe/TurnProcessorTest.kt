package com.aib.tictactoe

import com.aib.tictactoe.model.turnProcessor.EndingChecker
import com.aib.tictactoe.model.turnProcessor.TurnProcessor
import com.aib.tictactoe.model.data.Chip
import com.aib.tictactoe.model.data.EndGameStatus
import com.aib.tictactoe.view.CellFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TurnProcessorTest {

    private lateinit var turnProcessor: TurnProcessor
    private lateinit var endingChecker: EndingChecker
    private lateinit var nextTurnListener: NextTurnListener
    private lateinit var onWinListener: OnWinListener

    @Before
    fun setUp() {
        turnProcessor = TurnProcessor()
        endingChecker = object : EndingChecker {
            var cellFragment : CellFragment? = null
            override fun onMove(cell: CellFragment) {
                cellFragment = cell
            }
        }
        nextTurnListener = object : NextTurnListener {
            var turn = Chip.EMPTY
            override fun onNextTurn(turn: Chip) {
                this.turn = turn
            }
        }
        onWinListener = object : OnWinListener {
            var test = 0
            override fun onDraw() {
                test = 1
            }

            override fun onCrossesWin() {
                test = 2
            }

            override fun onNoughtWin() {
                test = 3
            }
        }
        turnProcessor.setEndingChecker(endingChecker)
        turnProcessor.addNextTurnListener(nextTurnListener)
        turnProcessor.addOnWinListener(onWinListener)
    }

    @Test
    fun testOnClickUpdatesCellState() {
        val cell = CellFragment(0, 0)
        cell.state = Chip.EMPTY

        turnProcessor.onClick(cell)

        assertEquals(Chip.CROSS, cell.state)
    }

    @Test
    fun testGameStatusCondition() {
        val cell = CellFragment(0,0)
        cell.state = Chip.EMPTY
        val field = TurnProcessor::class.java.getDeclaredField("gameStatus")
        field.isAccessible = true
        field.set(turnProcessor, EndGameStatus.DRAW)

        turnProcessor.onClick(cell)
        assertEquals(Chip.EMPTY, cell.state)
    }

    @Test
    fun testNotEmptyCellCondition() {
        val cell = CellFragment(0, 0)
        cell.state = Chip.NOUGHT

        turnProcessor.onClick(cell)
        assertEquals(Chip.NOUGHT, cell.state)
    }

    @Test
    fun testOnGameStatusUpdate() {
        turnProcessor.onGameStatusUpdate(EndGameStatus.DRAW)
        assertEquals(EndGameStatus.DRAW, turnProcessor.endGameStatus)
    }

    @Test
    fun testOnGameStatusWhenStatusIsPlay() {
        turnProcessor.onGameStatusUpdate(EndGameStatus.PLAY)

        assertEquals(Chip.NOUGHT, turnProcessor.currentTurn)
    }

    @Test
    fun testOnGameStatusWhenStatusIsNotPlay() {
        turnProcessor.onGameStatusUpdate(EndGameStatus.DRAW)
        val field = TurnProcessor::class.java.getDeclaredField("onWinListeners")
        field.isAccessible = true
        var onWinListener = (field.get(turnProcessor) as ArrayList<*>)[0]
        val fieldTest = onWinListener.javaClass.getDeclaredField("test")
        fieldTest.isAccessible = true
        val fieldValue: Int = fieldTest.get(onWinListener) as Int

        assertEquals(1, fieldValue)
    }

    @Test
    fun testClear_ResetsGameStatusAndCurrentTurn() {
        turnProcessor.clear()

        assertEquals(EndGameStatus.PLAY, turnProcessor.endGameStatus)
        assertEquals(Chip.CROSS, turnProcessor.currentTurn)
    }

    @Test
    fun testSwitchCurrentTurn() {
        val field = TurnProcessor::class.java.getDeclaredField("currentTurn")
        field.isAccessible = true
        field.set(turnProcessor, Chip.CROSS)

        val method = TurnProcessor::class.java.getDeclaredMethod("nextTurn")
        method.isAccessible = true
        method.invoke(turnProcessor)

        assertEquals(Chip.NOUGHT, turnProcessor.currentTurn)
    }

    @Test
    fun testNextTurnNotifiesListener() {
        val method = TurnProcessor::class.java.getDeclaredMethod("nextTurn")
        method.isAccessible = true
        method.invoke(turnProcessor)

        val fieldTest = nextTurnListener.javaClass.getDeclaredField("turn")
        fieldTest.isAccessible = true
        val fieldValue = fieldTest.get(nextTurnListener) as Chip

        assertEquals(turnProcessor.currentTurn, fieldValue)
    }

    @Test
    fun testNotifyOnNextTurnListenerCallsListener() {
        val method = TurnProcessor::class.java.getDeclaredMethod("notifyOnNextTurnListener")
        method.isAccessible = true
        method.invoke(turnProcessor)

        val fieldTest = nextTurnListener.javaClass.getDeclaredField("turn")
        fieldTest.isAccessible = true
        val turnResult = fieldTest.get(nextTurnListener) as Chip

        assertEquals(Chip.CROSS, turnResult)
    }

    @Test
    fun testNotifyOnWinListenerCallsOnDraw() {
        val field = TurnProcessor::class.java.getDeclaredField("gameStatus")
        field.isAccessible = true
        field.set(turnProcessor, EndGameStatus.DRAW)

        val method = TurnProcessor::class.java.getDeclaredMethod("notifyOnWinListener")
        method.isAccessible = true
        method.invoke(turnProcessor)

        val fieldTest = onWinListener.javaClass.getDeclaredField("test")
        fieldTest.isAccessible = true
        val fieldValue = fieldTest.get(onWinListener)
        assertEquals(1, fieldValue)
    }

    @Test
    fun testNotifyOnWinListenerCallsOnNoughtWin() {
        val field = TurnProcessor::class.java.getDeclaredField("gameStatus")
        field.isAccessible = true
        field.set(turnProcessor, EndGameStatus.NOUGHTS_WIN)

        val method = TurnProcessor::class.java.getDeclaredMethod("notifyOnWinListener")
        method.isAccessible = true
        method.invoke(turnProcessor)

        val fieldTest = onWinListener.javaClass.getDeclaredField("test")
        fieldTest.isAccessible = true
        val fieldValue = fieldTest.get(onWinListener)
        assertEquals(3, fieldValue)
    }

    @Test
    fun testNotifyOnWinListenerCallsOnCrossesWin() {
        val field = TurnProcessor::class.java.getDeclaredField("gameStatus")
        field.isAccessible = true
        field.set(turnProcessor, EndGameStatus.CROSSES_WIN)

        val method = TurnProcessor::class.java.getDeclaredMethod("notifyOnWinListener")
        method.isAccessible = true
        method.invoke(turnProcessor)

        val fieldTest = onWinListener.javaClass.getDeclaredField("test")
        fieldTest.isAccessible = true
        val fieldValue = fieldTest.get(onWinListener)
        assertEquals(2, fieldValue)
    }

    @Test
    fun testOnNewGameCallsClear() {
        val field = TurnProcessor::class.java.getDeclaredField("gameStatus")
        field.isAccessible = true
        field.set(turnProcessor, EndGameStatus.DRAW)
        turnProcessor.onNewGame()

        assertEquals(EndGameStatus.PLAY, turnProcessor.endGameStatus)
    }
}