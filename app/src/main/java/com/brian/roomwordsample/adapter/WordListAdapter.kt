package com.brian.roomwordsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brian.roomwordsample.R
import com.brian.roomwordsample.entity.Word

/**
 * @author Brian
 * @date: 3/24/19
 */


class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mWords: List<Word>? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(mInflater.inflate(R.layout.recycler_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mWords?.size ?: 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.text = current.mWord

        } else {
            holder.wordItemView.text = "No word"
        }
    }

    fun setWords(words: List<Word>?) {
        mWords = words
        notifyDataSetChanged()
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val wordItemView : TextView by lazy {
            itemView.findViewById<TextView>(R.id.textView)
        }


    }
}