package com.example.androiduitesting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_text_actitvity.*


class EditTextActitvity : AppCompatActivity() {

    companion object {
        val EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"
        val TEXT_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_actitvity)
    }

    fun launchSecondActivity(view: View) {
        val intent = Intent(this@EditTextActitvity, NextTextActivity::class.java)
        intent.apply {
            putExtra(EXTRA_MESSAGE, editText_main.text.toString())
        }
        startActivityForResult(intent, TEXT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                val reply: String = data!!.getStringExtra(NextTextActivity.EXTRA_REPLY)!!

                // Make the reply head visible.

                // Make the reply head visible.
                text_header_reply.visibility = View.VISIBLE

                // Set the reply and make it visible.

                // Set the reply and make it visible.
                text_message_reply.text = reply
                text_message_reply.visibility = View.VISIBLE
            }
        }
    }
}