package com.jamascrorp.testproject.presentation.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.presentation.recyclerview.DiffUtilItemCallback
import com.jamascrorp.testproject.presentation.recyclerview.viewholders.BookmarksViewHolder

class BookmarksAdapter : ListAdapter<SavedText, BookmarksViewHolder>(DiffUtilItemCallback()) {
    var select: ((string: String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.news_cardview, parent, false)
        return BookmarksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            select?.invoke(item.url)
        }
    }
}