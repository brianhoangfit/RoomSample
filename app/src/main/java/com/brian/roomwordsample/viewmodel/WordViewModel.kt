package com.brian.roomwordsample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.brian.roomwordsample.entity.Word
import com.brian.roomwordsample.repository.WordRepository

/**
 * @author Brian
 * @date: 3/24/19
 */


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository = WordRepository(application)

    private val mAllWords: LiveData<List<Word>>

    init {
        mAllWords = mRepository.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> = mAllWords

    fun insert(word: Word) = mRepository.insert(word)


}