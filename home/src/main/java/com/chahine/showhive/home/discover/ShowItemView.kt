package com.chahine.showhive.home.discover

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemShowBinding
import javax.inject.Inject

class ShowItemView {

    class Delegate @Inject constructor() : DiscoverAdapter.Delegate {

        override fun layoutId() = R.layout.item_show

        override fun create(parent: ViewGroup) = Holder(itemView(parent))

        override fun bind(item: DiscoverAdapter.Item, holder: RecyclerView.ViewHolder) {
            if (holder is Holder && item is Item) {
                holder.bind(item)
            }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemShowBinding.bind(itemView)

        fun bind(item: Item) = with(binding) {
            body.text = item.body
        }
    }

    data class Item(val body: CharSequence) : DiscoverAdapter.Item {
        override fun itemViewType() = DiscoverAdapter.SHOW
    }
}
