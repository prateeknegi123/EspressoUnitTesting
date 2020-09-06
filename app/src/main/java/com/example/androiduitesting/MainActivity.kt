package com.example.androiduitesting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var mScore1 = 0
    private var mScore2 = 0

    companion object {
        const val STATE_SCORE_1 = "Team 1 Score"
        const val STATE_SCORE_2 = "Team 2 Score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)

            score_1.text = mScore1.toString()
            score_2.text = mScore2.toString()
        }
    }

    fun decreaseScore(view: View) {
        val viewID = view.id
        when (viewID) {
            R.id.decreaseTeam1 -> {
                mScore1--
                score_1.text = mScore1.toString()
            }

            R.id.decreaseTeam2 -> {
                mScore2--;
                score_2.text = mScore2.toString()
            }
        }
    }

    fun increaseScore(view: View) {
        when (view.id) {
            R.id.increaseTeam1 -> {
                mScore1++;
                score_1.text = mScore1.toString()
            }
            R.id.increaseTeam2 -> {
                mScore2++;
                score_2.text = mScore2.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the scores.

        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu!!.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu!!.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }

        return true
    }
}