package com.example.androiduitesting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class WordViewModel(mApplication: Application) : AndroidViewModel(mApplication) {

    companion object{
        val FACTORY = singleArgViewModelFactory(::WordViewModel)
    }


    private var mRepository: WordRepository? = null
    private var mAllWords: LiveData<List<Word?>>? = null

    init {
        mRepository = WordRepository(mApplication)
        mAllWords = mRepository!!.words
    }

    val getAllWords: LiveData<List<Word?>>
        get() = mAllWords!!

    fun insert(word: Word) {
        mRepository!!.insert(word)
    }
}