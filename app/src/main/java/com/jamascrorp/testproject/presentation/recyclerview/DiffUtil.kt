package com.jamascrorp.testproject.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.jamascrorp.testproject.data.network.Model1

class DiffUtil : DiffUtil.ItemCallback<Model1>() {
    override fun areItemsTheSame(oldItem: Model1, newItem: Model1): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Model1, newItem: Model1): Boolean {
        return oldItem.id == newItem.id
    }
}