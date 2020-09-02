package com.example.androiduitesting

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private var db: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase {
            if (db == null) {
                db = buildDbInstance(context)
            }
            return db!!
        }

        private fun buildDbInstance(context: Context): WordRoomDatabase {
            return Room.databaseBuilder(
                context,
                WordRoomDatabase::class.java, "word_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}