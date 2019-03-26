package com.brian.roomwordsample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.brian.roomwordsample.dao.WordDao
import com.brian.roomwordsample.db.WordRoomDatabase
import com.brian.roomwordsample.entity.Word
import org.jetbrains.anko.doAsync

/**
 * @author Brian
 * @date: 3/24/19
 */


class WordRepository(application: Application) {

    private var mWordDao : WordDao
    private var mAllWords: LiveData<List<Word>>

    init {
        val wordRoomDatabase = WordRoomDatabase.getInstance(application)
        mWordDao = wordRoomDatabase.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords() : LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        doAsync {
            mWordDao.insert(word)
        }
    }

}