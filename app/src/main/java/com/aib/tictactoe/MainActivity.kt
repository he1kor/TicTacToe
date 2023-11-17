package com.aib.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aib.tictactoe.databinding.ActivityMainBinding
import com.aib.tictactoe.view.TableFragment

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private lateinit var tableFragment: TableFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTable()
        (application as TicTacToeApplication).models.gameCreator.create()
    }
    private fun initTable(){
        tableFragment = supportFragmentManager.findFragmentById(R.id.table) as TableFragment
    }
}