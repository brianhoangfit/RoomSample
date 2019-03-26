package com.brian.roomwordsample.db

import android.content.Context
import androidx.room.*
import com.brian.roomwordsample.dao.WordDao
import com.brian.roomwordsample.entity.Word

/**
 * @author Brian
 * @date: 3/24/19
 */

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private const val DATABASE_NAME = "word_db"

        // For singleton instantiation
        @Volatile private var instances : WordRoomDatabase? = null

        fun getInstance(context: Context) : WordRoomDatabase {
            return instances ?: synchronized(this) {
                buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context) : WordRoomDatabase {
            return Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }

}