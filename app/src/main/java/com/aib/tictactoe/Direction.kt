package com.aib.tictactoe

data class Direction(var row: Int, var col: Int){
    fun newReverse() : Direction{
        return Direction(-row, -col)
    }
}
