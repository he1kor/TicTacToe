package com.aib.tictactoe.model.winCheker

import com.aib.tictactoe.model.data.EndingStatus

interface EndingStatusListener {
    fun onGameStatusChecked(endGameStatus: EndingStatus)
}
