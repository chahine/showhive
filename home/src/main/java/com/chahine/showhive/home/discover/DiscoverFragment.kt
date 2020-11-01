package com.chahine.showhive.home.discover

import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.home.HomeActivity
import com.chahine.showhive.home.R

class DiscoverFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_discover

    override fun setUpDependencyInjection() {
        if (activity is HomeActivity) {
            (activity as HomeActivity).component.inject(this)
        }
    }
}
