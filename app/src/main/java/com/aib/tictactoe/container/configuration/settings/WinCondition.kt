package com.aib.tictactoe.container.configuration.settings

import com.aib.tictactoe.model.data.Vector

class WinCondition(
    var winCellsAmount: Int = 4,
    var shiftVectors: Array<Vector> = arrayOf(
        Vector(0, 1),
        Vector(1, 0),
        Vector(1, 1),
        Vector(1, -1)
    )
)