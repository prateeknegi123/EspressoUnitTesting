package com.example.androiduitesting

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class WordRepository(application: Application) {

    private var wordDao: WordDao? = null
    private var wordList: LiveData<List<Word?>>? = null
    private var executors : Executor = Executors.newFixedThreadPool(2)

    val words: LiveData<List<Word?>>
        get() = wordList!!

    init {
        val wordRoomDatabase = WordRoomDatabase.getInstance(application)
        wordDao = wordRoomDatabase.wordDao()
        wordList = wordDao!!.getAllWords()
    }

    fun insert(word: Word){
        executors.execute {
            wordDao!!.insertWord(word)
        }
    }
}