package com.example.androiduitesting

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wordlist_item.view.*

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(text: String) {
        itemView.word.text = text
    }
}