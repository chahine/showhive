package com.chahine.showhive.home.discover

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemImageLineThreeBinding
import javax.inject.Inject

class ShowItemView {

    class Delegate @Inject constructor() : DiscoverAdapter.Delegate {

        override fun layoutId() = R.layout.item_image_line_three

        override fun create(parent: ViewGroup) = Holder(itemView(parent))

        override fun bind(item: DiscoverAdapter.Item, holder: RecyclerView.ViewHolder) {
            if (holder is Holder && item is Item) {
                holder.bind(item)
            }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemImageLineThreeBinding.bind(itemView)

        fun bind(item: Item) = with(binding) {
            line1.text = item.line1
            line2.text = item.line2
            line3.text = item.line3
        }
    }

    data class Item(val line1: CharSequence, val line2: CharSequence, val line3: CharSequence) : DiscoverAdapter.Item {
        override fun itemViewType() = DiscoverAdapter.SHOW
    }
}
