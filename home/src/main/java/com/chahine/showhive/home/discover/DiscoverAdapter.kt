package com.chahine.showhive.home.discover

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chahine.showhive.base.rv.RvAdapter
import com.chahine.showhive.base.rv.RvDelegate
import com.chahine.showhive.base.rv.RvItem

class DiscoverAdapter(
    diffCallback: DiffUtil.ItemCallback<Item>,
    delegates: Map<Int, Delegate>
) : RvAdapter<DiscoverAdapter.Item>(diffCallback, delegates) {

    interface Delegate : RvDelegate<Item, RecyclerView.ViewHolder>
    interface Item : RvItem

    companion object {
        const val LOADING = 1
        const val SHOW = 2
    }
}
