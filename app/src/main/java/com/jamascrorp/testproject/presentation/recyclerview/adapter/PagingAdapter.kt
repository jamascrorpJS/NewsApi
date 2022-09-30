package com.jamascrorp.testproject.presentation.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.presentation.recyclerview.DiffUtil
import com.jamascrorp.testproject.presentation.recyclerview.viewholders.SourcesNewsViewHolder


class PagingAdapter : PagingDataAdapter<Model1, SourcesNewsViewHolder>(DiffUtil()) {
    var select: ((model: Model1) -> Unit)? = null
    override fun onBindViewHolder(holder: SourcesNewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
        holder.itemView.setOnClickListener {
            select?.invoke(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.news_cardview, parent, false)
        return SourcesNewsViewHolder(view)
    }
}