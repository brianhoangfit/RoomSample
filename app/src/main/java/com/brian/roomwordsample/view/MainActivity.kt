package com.brian.roomwordsample.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.brian.roomwordsample.R
import com.brian.roomwordsample.adapter.WordListAdapter
import com.brian.roomwordsample.entity.Word
import com.brian.roomwordsample.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private var mWordViewModel: WordViewModel? = null

    companion object {

        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        val mWordAdapter = WordListAdapter(this)

        recyclerview.apply {
            setHasFixedSize(true)
            adapter = mWordAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        mWordViewModel?.getAllWords()?.observe(this,
            Observer<List<Word>> { t -> mWordAdapter.setWords(t) })

        fab.setOnClickListener {

            val newWordIntent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(newWordIntent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
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
