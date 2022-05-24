package com.chahine.showhive.base.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class RvAdapter<E : RvItem>(
    diffCallback: DiffUtil.ItemCallback<E>,
    private val delegates: Map<Int, RvDelegate<E, ViewHolder>>
) : ListAdapter<E, ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return delegates[viewType]?.create(parent) ?: error("cant find delegate for view type #$viewType")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adapterPosition = holder.bindingAdapterPosition
        val viewType = getItemViewType(adapterPosition)
        delegates[viewType]?.bind(getItem(adapterPosition), holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
        val adapterPosition = holder.bindingAdapterPosition
        val viewType = getItemViewType(adapterPosition)
        delegates[viewType]?.bind(getItem(adapterPosition), holder, payloads)
    }

    override fun getItemViewType(position: Int) = getItem(position).itemViewType()
}
