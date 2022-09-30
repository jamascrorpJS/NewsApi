package com.jamascrorp.testproject.presentation.recyclerview.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.network.Model1
import javax.inject.Inject

class SourcesNewsViewHolder @Inject constructor(view: View) : RecyclerView.ViewHolder(view) {

    val descript = view.findViewById<TextView>(R.id.text_description)
    val title = view.findViewById<TextView>(R.id.text_name)
    val image = view.findViewById<ImageView>(R.id.imageView)

    fun bind(item: Model1) {
        descript.text = item.title
        title.text = item.author
        Glide.with(itemView.context)
            .load(item.image)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(image)
    }
}