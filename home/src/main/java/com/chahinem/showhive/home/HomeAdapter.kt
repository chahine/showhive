package com.chahinem.showhive.home

import android.support.v7.widget.RecyclerView.ViewHolder
import com.chahinem.showhive.base.rv.RvAdapter
import com.chahinem.showhive.base.rv.RvDelegate
import com.chahinem.showhive.base.rv.RvItem
import com.chahinem.showhive.home.HomeAdapter.Item
import com.squareup.picasso.Picasso
import javax.inject.Inject

class HomeAdapter @Inject constructor(picasso: Picasso) : RvAdapter<Item>() {

  init {
    delegates[1] = ShowItemView.Delegate(picasso)
  }

  interface Delegate : RvDelegate<Item, ViewHolder>
  interface Item : RvItem
}
