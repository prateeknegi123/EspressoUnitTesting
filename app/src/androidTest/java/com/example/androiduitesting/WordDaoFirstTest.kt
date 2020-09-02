package com.example.androiduitesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class WordDaoFirstTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var wordDao: WordDao? = null


    @Before
    fun setUp() {
        wordDao =
            WordRoomDatabase.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
                .wordDao()
    }

    @Test
    fun insertAndGetWord() {
        val word = Word("Prateek")
        wordDao!!.insertWord(word)

        val wordList = LiveDataTestUtil.getValue(wordDao!!.getAllWords()!!)
        assertEquals(wordList!![0]!!.word, word.word)
    }

    @Test
    fun getAllWord(){
        val word = Word("Prateek")
        wordDao!!.insertWord(word)
        val word2 = Word("Prateek Again")
        wordDao!!.insertWord(word2)


        val list = LiveDataTestUtil.getValue(wordDao!!.getAllWords()!!)
        assertEquals(list!![0]!!.word, word.word)
        assertEquals(list[1]!!.word, word2.word)
    }

    @After
    fun tearDown() {

    }
}