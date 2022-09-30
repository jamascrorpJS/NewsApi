package com.jamascrorp.testproject.presentation.recyclerview.viewholders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.domain.entity.Categories

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val text = view.findViewById<TextView>(R.id.text_category)
    val cardView = view.findViewById<CardView>(R.id.category_cardview)

    fun bind(item: Categories) {
        text.text = item.name.replaceFirstChar { it.uppercase() }
        cardView.setCardBackgroundColor(item.color)
    }
}