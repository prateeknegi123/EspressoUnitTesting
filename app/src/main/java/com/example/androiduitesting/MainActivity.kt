package com.example.androiduitesting

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val mWordList: LinkedList<String> = LinkedList()
    private val mainAdapter = MainAddapter(mWordList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..19) {
            mWordList.addLast("Word $i")
        }

        fab.setOnClickListener {
            val wordListSize = mWordList.size
            // Add a new word to the wordList.
            mWordList.addLast("+ Word $wordListSize")
            // Notify the adapter, that the data has changed.
            content.recyclerview.adapter!!.notifyItemInserted(wordListSize)
            // Scroll to the bottom.
            content.recyclerview.smoothScrollToPosition(wordListSize)
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {

        content.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
    }
}


