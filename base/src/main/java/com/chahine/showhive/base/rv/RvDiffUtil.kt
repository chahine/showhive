package com.chahine.showhive.base.rv

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

class RvDiffUtil<E : RvItem> @Inject constructor() : DiffUtil.ItemCallback<E>() {
    override fun areItemsTheSame(oldItem: E, newItem: E): Boolean {
        return oldItem.itemViewType() == newItem.itemViewType()
    }

    override fun areContentsTheSame(oldItem: E, newItem: E): Boolean {
        return oldItem.equals(newItem)
    }
}
