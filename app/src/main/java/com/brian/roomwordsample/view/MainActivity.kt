package com.brian.roomwordsample.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brian.roomwordsample.BR
import com.brian.roomwordsample.R
import com.brian.roomwordsample.adapter.WordListAdapter
import com.brian.roomwordsample.databinding.ActivityMainBinding
import com.brian.roomwordsample.entity.Word
import com.brian.roomwordsample.viewmodel.WordViewModel
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private var mWordViewModel: WordViewModel? = null

    private lateinit var viewBinding: ActivityMainBinding

    companion object {

        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        val mWordAdapter = WordListAdapter(this)

        viewBinding.apply {
            setVariable(BR.fabOnClick, View.OnClickListener {
                addNewWord()
            })

            includeContentMain.apply {
                hasFixedSize = true
                wordAdapter = mWordAdapter
            }
        }

        mWordViewModel?.getAllWords()?.observe(this,
            Observer<List<Word>> { t -> mWordAdapter.setWords(t) })
    }

    private fun addNewWord() {
        val newWordIntent = Intent(this@MainActivity, NewWordActivity::class.java)
        startActivityForResult(newWordIntent, NEW_WORD_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data?.getStringExtra(NewWordActivity.EXTRA_REPLY) ?: "NA")
            mWordViewModel?.insert(word)
        } else {
            toast(R.string.empty_not_saved)
        }
    }
}
