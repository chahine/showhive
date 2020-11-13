package com.chahine.showhive.home.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.chahine.showhive.base.rv.RvItem
import com.chahine.showhive.home.R
import com.chahine.trakt.entities.TrendingShow

class DiscoverAdapter(
    diffCallback: DiffUtil.ItemCallback<TrendingShow>
) : PagingDataAdapter<TrendingShow, ShowItemView.Holder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowItemView.Holder {
        return ShowItemView.Holder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_image_line_three, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShowItemView.Holder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    interface Item : RvItem

    companion object {
        const val LOADING = 1
        const val SHOW = 2
    }
}
