package com.aib.tictactoe

import android.app.Application
import com.aib.tictactoe.container.DefaultModelContainer
import com.aib.tictactoe.container.RepositoryContainer
import com.aib.tictactoe.container.DefaultRepositoryContainer
import com.aib.tictactoe.container.ModelContainer

class TicTacToeApplication : Application() {
    lateinit var repositories: RepositoryContainer
    lateinit var models: ModelContainer
    override fun onCreate() {
        super.onCreate()
        repositories = DefaultRepositoryContainer()
        models = DefaultModelContainer()

        Linker.link(repositories, models)
        models.gameCreator.create()
    }
}