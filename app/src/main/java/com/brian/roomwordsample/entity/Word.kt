package com.brian.roomwordsample.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Brian
 * @date: 3/24/19
 */

@Entity(tableName = "word_table")
data class Word(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    var mWord: String
)