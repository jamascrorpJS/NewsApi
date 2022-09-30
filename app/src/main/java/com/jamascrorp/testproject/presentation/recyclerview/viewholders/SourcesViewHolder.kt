package com.jamascrorp.testproject.presentation.recyclerview.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.models.Model

class SourcesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.text_sources)
    val categ = view.findViewById<TextView>(R.id.text_sources1)
    val image = view.findViewById<ImageView>(R.id.image_view)

    fun bind(item: Model) {
        name.text = item.name
        categ.text = item.category?.replaceFirstChar { it.uppercase() }
    }

    fun image() {
        when (categ.text) {
            "General" -> image.setImageResource(R.mipmap.general)
            "Business" -> image.setImageResource(R.mipmap.business)
            "Science" -> image.setImageResource(R.mipmap.science)
            "Technology" -> image.setImageResource(R.mipmap.technology)
            "Health" -> image.setImageResource(R.mipmap.health)
            "Entertainment" -> image.setImageResource(R.mipmap.entertainment)
            "Sports" -> image.setImageResource(R.mipmap.sport)
        }
    }
}