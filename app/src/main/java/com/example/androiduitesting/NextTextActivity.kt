package com.example.androiduitesting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_next_text.*

class NextTextActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_text)

        val intent = intent
        val message = intent.getStringExtra(EditTextActitvity.EXTRA_MESSAGE)
        text_message.text = message
    }

    fun returnReply(view: View) {
        val reply = editText_second.text.toString()
        val intent = Intent().apply {
            putExtra(EXTRA_REPLY, reply)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}