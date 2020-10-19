package com.chahinem.showhive.home.profile

import com.chahinem.showhive.base.BaseFragment
import com.chahinem.showhive.home.HomeActivity
import com.chahinem.showhive.home.R

class ProfileFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_profile

    override fun setUpDependencyInjection() {
        if (activity is HomeActivity) {
            (activity as HomeActivity).component.inject(this)
        }
    }
}
