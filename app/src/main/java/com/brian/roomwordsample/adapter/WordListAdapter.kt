package com.brian.roomwordsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.brian.roomwordsample.R
import com.brian.roomwordsample.databinding.RecyclerViewItemBinding
import com.brian.roomwordsample.entity.Word

/**
 * @author Brian
 * @date: 3/24/19
 */


class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mWords: List<Word>? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(DataBindingUtil.inflate(mInflater, R.layout.recycler_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mWords?.size ?: 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.bind(current)

        } else {
            holder.bind(Word("No Word"))
        }
    }

    fun setWords(words: List<Word>?) {
        mWords = words
        notifyDataSetChanged()
    }

    class WordViewHolder(private val itemBinding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Word) {
            with(itemBinding) {
                word = item
            }
        }


    }
}