package com.example.androiduitesting

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        when(viewID){
            R.id.decreaseTeam1->{
                mScore1--
                score_1.text = mScore1.toString()
            }

            R.id.decreaseTeam2->{
                mScore2--;
                score_2.text = mScore2.toString()
            }
        }
    }

    fun increaseScore(view: View) {
        when(view.id){
            R.id.increaseTeam1->{
                mScore1++;
                score_1.text = mScore1.toString()
            }
            R.id.increaseTeam2->{
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
}