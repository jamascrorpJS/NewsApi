package com.jamascrorp.testproject.presentation.recyclerview.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.db.SavedText

class BookmarksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val text = view.findViewById<TextView>(R.id.text_description)
    val text1 = view.findViewById<TextView>(R.id.text_name)
    val image = view.findViewById<ImageView>(R.id.imageView)

    fun bind(item: SavedText) {

        text.text = item.title
        text1.text = item.author
        Glide.with(itemView)
            .load(item.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(image)
    }
}