package com.brian.roomwordsample.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Brian
 * @date: 4/15/19
 */
object BindingAdapters {

    @BindingAdapter("app:hasFixedSize")
    @JvmStatic
    fun setHaxFixedSize(recyclerView: RecyclerView, hasFixedSize: Boolean) {
        recyclerView.setHasFixedSize(hasFixedSize)
    }

}
