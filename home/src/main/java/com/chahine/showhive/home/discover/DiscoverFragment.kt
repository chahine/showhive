package com.chahine.showhive.home.discover

import android.os.Bundle
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.home.HomeActivity
import com.chahine.showhive.home.R
import com.google.android.material.transition.MaterialFadeThrough

class DiscoverFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_discover

    override fun setUpDependencyInjection() {
        (requireActivity() as HomeActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }
}
