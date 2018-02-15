package com.chahinem.showhive.home

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chahinem.showhive.base.Router
import com.squareup.picasso.Picasso

class ShowItemView {
  class Delegate(private val router: Router,
                 private val picasso: Picasso) : HomeAdapter.Delegate, ClickListener {
    override fun layoutId() = R.layout.item_show

    override fun create(parent: ViewGroup) = Holder(itemView(parent), this)

    override fun bind(item: HomeAdapter.Item, holder: RecyclerView.ViewHolder) {
      if (holder is Holder && item is Item) {
        holder.bind(item, picasso)
      }
    }

    override fun onItemClick(traktId: Int?) {
      router.tvShow(traktId.toString())
    }
  }

  class Holder(itemView: View, private val listener: ClickListener) : ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.image)

    private var traktId: Int? = null

    init {
      itemView.setOnClickListener { listener.onItemClick(traktId) }
    }

    fun bind(item: Item, picasso: Picasso) {
      traktId = item.traktId
      item.image?.let {
        picasso
            .load("https://image.tmdb.org/t/p/w500/$it")
            .placeholder(com.chahinem.showhive.base.R.color.colorAccent)
            .error(com.chahinem.showhive.base.R.color.colorPrimaryDark)
            .centerCrop()
            .fit()
            .into(image)
      }
    }
  }

  data class Item(val traktId: Int?, val image: String? = null) : HomeAdapter.Item {
    override fun itemViewType() = 1
  }

  interface ClickListener {
    fun onItemClick(traktId: Int?)
  }
}
