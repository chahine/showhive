package com.chahinem.showhive.home.discover

import com.chahinem.showhive.base.BaseFragment
import com.chahinem.showhive.home.HomeActivity
import com.chahinem.showhive.home.R

class DiscoverFragment : BaseFragment() {

  override fun getLayoutId() = R.layout.fragment_discover

  override fun setUpDependencyInjection() {
    if (activity is HomeActivity) {
      (activity as HomeActivity).component.inject(this)
    }
  }
}