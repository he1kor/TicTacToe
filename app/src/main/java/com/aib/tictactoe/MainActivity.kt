package com.aib.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.aib.tictactoe.databinding.ActivityMainBinding
import com.aib.tictactoe.view.gamePart.GamePartFragment
import com.aib.tictactoe.view.gamePart.SettingsFragment

class MainActivity : AppCompatActivity(), Navigator{
    override var lastActivityPart: ActivityPart = ActivityPart.GAME

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as TicTacToeApplication).models.gameCreator.create()
    }


    override fun navigate(activityPart: ActivityPart) {
        if (activityPart == lastActivityPart)
            return

        when (activityPart){
            ActivityPart.GAME -> replaceFragment<GamePartFragment>()
            ActivityPart.SETTINGS -> replaceFragment<SettingsFragment>()
        }
        lastActivityPart = activityPart
    }

    private inline fun <reified T : Fragment> replaceFragment(){
        val tag = System.currentTimeMillis().toString()
        supportFragmentManager.commit {
            replace<T>(binding.fragmentContainer.id, tag)
            addToBackStack(tag)
        }
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}