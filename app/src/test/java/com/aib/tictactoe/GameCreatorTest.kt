package com.aib.tictactoe

import com.aib.tictactoe.listener.NewGameListener
import com.aib.tictactoe.logic.GameCreator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field

class GameCreatorTest {

    private lateinit var gameCreator: GameCreator

    @Before
    fun setUp() {
        gameCreator = GameCreator()
    }

    @Test
    fun testAddNewGameListener() {
        val newGameListener = object : NewGameListener {
            override fun onNewGame() {
            }
        }

        gameCreator.addNewGameListener(newGameListener)

        val newGameListenersField: Field = GameCreator::class.java.getDeclaredField("newGameListeners")
        newGameListenersField.isAccessible = true
        val newGameListeners: List<NewGameListener> = newGameListenersField.get(gameCreator) as List<NewGameListener>

        assertEquals(1, newGameListeners.size)
        assertEquals(newGameListener, newGameListeners[0])
    }

    @Test
    fun testRemoveNewGameListener() {
        val newGameListener = object : NewGameListener {
            override fun onNewGame() {
            }
        }

        gameCreator.addNewGameListener(newGameListener)
        gameCreator.removeNewGameListener(newGameListener)

        val newGameListenersField: Field = GameCreator::class.java.getDeclaredField("newGameListeners")
        newGameListenersField.isAccessible = true
        val newGameListeners: List<NewGameListener> = newGameListenersField.get(gameCreator) as List<NewGameListener>

        assertEquals(0, newGameListeners.size)
    }

    @Test
    fun testCreate() {
        val newGameListener = object : NewGameListener {
            override fun onNewGame() {
            }
        }
        gameCreator.addNewGameListener(newGameListener)
        gameCreator.create()
    }
}