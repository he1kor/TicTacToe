package com.aib.tictactoe

import android.app.Application
import com.aib.tictactoe.container.RepositoryContainer
import com.aib.tictactoe.container.DefaultRepositoryContainer

class TicTacToeApplication : Application() {
    lateinit var container: RepositoryContainer
    lateinit var modelInitializer: ModelInitializer
    override fun onCreate() {
        super.onCreate()
        container = DefaultRepositoryContainer()
        modelInitializer = ModelInitializer(container)
        modelInitializer.initialize()
        modelInitializer.link()
    }
}