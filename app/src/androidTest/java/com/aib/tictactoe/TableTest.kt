package com.aib.tictactoe

import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
//class TableTest {
//
//    private lateinit var appCompatActivity: AppCompatActivity
//    private lateinit var table: Table
//
//    @Suppress("DEPRECATION")
//    @get:Rule
//    val activityTestRule = ActivityTestRule<AppCompatActivity>(
//        AppCompatActivity::class.java
//    )
//    @Before
//    fun setUp() {
//        appCompatActivity = activityTestRule.activity
//    }
//
//    @Test
//    fun testCreate() {
//        table.create(appCompatActivity, 3)
//
//        assertEquals(9, table.cellCount)
//        assertEquals(0, table.filledCells)
//        assertEquals(3, table.cellMatrix.size)
//        assertEquals(3, table.cellMatrix[0].size)
//        assertEquals(3, table.cellMatrix[1].size)
//        assertEquals(3, table.cellMatrix[2].size)
//    }
//
//    @Test
//    fun testSetOnButtonClickListener() {
//        val onClickListener = object : CellFragment.OnClickListener {
//            override fun onClick(cell: CellFragment) {
//            }
//        }
//
//        table.create(appCompatActivity, 2)
//        table.setOnButtonClickListener(onClickListener)
//
//        for (row in table.cellMatrix) {
//            for (cell in row) {
//                val listenerField = cell.javaClass.getDeclaredField("onClickListener")
//                listenerField.isAccessible = true
//                val actualClickListener = listenerField.get(cell) as CellFragment.OnClickListener
//                assertEquals(onClickListener, actualClickListener)
//            }
//        }
//    }
//
//    @Test
//    fun testGetCellFragment() {
//        table.create(appCompatActivity, 2)
//
//        val cellFragment = table.getCellFragment(1, 3)
//
//        assertEquals(1, cellFragment.row)
//        assertEquals(3, cellFragment.column)
//    }
//
//    @Test
//    fun testClear() {
//        activityTestRule.runOnUiThread {
//            table.create(appCompatActivity, 2)
//        }
//
//        for (row in table.cellMatrix) {
//            for (cell in row) {
//                cell.state = Chip.CROSS
//            }
//        }
//
//        table.clear()
//
//        assertEquals(0, table.filledCells)
//
//        for (row in table.cellMatrix) {
//            for (cell in row) {
//                assertEquals(Chip.EMPTY, cell.state)
//            }
//        }
//    }
//
//    @Test
//    fun testOnNewGame() {
//    }
//
//    @Test
//    fun testOnFilled() {
//        table.create(appCompatActivity, 2)
//
//        table.onFilled(Chip.CROSS)
//        table.onFilled(Chip.NOUGHT)
//
//        assertEquals(2, table.filledCells)
//    }
//}