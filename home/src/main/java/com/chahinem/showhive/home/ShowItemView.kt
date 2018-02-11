package com.chahinem.showhive.home

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chahinem.trakt.entities.Show
import com.squareup.picasso.Picasso

class ShowItemView {
  class Delegate(private val picasso: Picasso) : HomeAdapter.Delegate {
    override fun layoutId() = R.layout.item_show

    override fun create(parent: ViewGroup) = Holder(itemView(parent))

    override fun bind(item: HomeAdapter.Item, holder: RecyclerView.ViewHolder) {
      if (holder is Holder && item is Item) {
        holder.bind(item, picasso)
      }
    }
  }

  class Holder(itemView: View) : ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.image)
    private val title: TextView = itemView.findViewById(R.id.title)

    fun bind(item: Item, picasso: Picasso) {
      title.text = item.show.title
    }
  }

  data class Item(val show: Show) : HomeAdapter.Item {
    override fun itemViewType() = 1
  }
}