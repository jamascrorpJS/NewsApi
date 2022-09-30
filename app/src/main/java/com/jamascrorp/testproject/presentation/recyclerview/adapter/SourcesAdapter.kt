package com.jamascrorp.testproject.presentation.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.data.models.Model
import com.jamascrorp.testproject.presentation.recyclerview.viewholders.SourcesViewHolder

class SourcesAdapter(private val list: List<Model>) : RecyclerView.Adapter<SourcesViewHolder>() {

    var select: ((string: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.sources_cardview, parent, false)
        return SourcesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.image()
        holder.itemView.setOnClickListener {
            select?.invoke(item.id)
        }
    }

    override fun getItemCount(): Int = list.size
}