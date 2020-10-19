package com.chahinem.showhive.base.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface RvDelegate<in E, V : ViewHolder> {

    @LayoutRes
    fun layoutId(): Int

    fun bind(item: E, holder: V)

    fun bind(item: E, holder: V, payloads: List<Any>) {
        bind(item, holder)
    }

    fun create(parent: ViewGroup): V

    fun itemView(parent: ViewGroup): View =
        LayoutInflater
            .from(parent.context)
            .inflate(layoutId(), parent, false)
}
