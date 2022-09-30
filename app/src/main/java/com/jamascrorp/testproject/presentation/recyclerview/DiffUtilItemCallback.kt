package com.jamascrorp.testproject.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.jamascrorp.testproject.data.db.SavedText

class DiffUtilItemCallback: DiffUtil.ItemCallback<SavedText>() {

    override fun areItemsTheSame(oldItem: SavedText, newItem: SavedText): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SavedText, newItem: SavedText): Boolean {
        return oldItem == newItem
    }
}