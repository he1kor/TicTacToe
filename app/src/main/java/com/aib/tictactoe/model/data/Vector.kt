package com.aib.tictactoe.model.data

data class Vector(
    var row: Int,
    var col: Int){
    fun reverse() : Vector {
        row *= -1
        col *= -1
        return this
    }
}
