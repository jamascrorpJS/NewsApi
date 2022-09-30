package com.jamascrorp.testproject.presentation.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.domain.entity.Categories
import com.jamascrorp.testproject.presentation.recyclerview.viewholders.CategoryViewHolder

class CategoryAdapter(var list: List<Categories>) : RecyclerView.Adapter<CategoryViewHolder>() {

    var select: ((string: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.category_cardview, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            select?.invoke(item.name)
        }
    }

    override fun getItemCount(): Int = list.size
}

