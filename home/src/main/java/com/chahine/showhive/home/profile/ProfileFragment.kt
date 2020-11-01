package com.chahine.showhive.home.profile

import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.home.HomeActivity
import com.chahine.showhive.home.R

class ProfileFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_profile

    override fun setUpDependencyInjection() {
        if (activity is HomeActivity) {
            (activity as HomeActivity).component.inject(this)
        }
    }
}
