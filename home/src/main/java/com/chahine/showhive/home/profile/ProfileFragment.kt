package com.chahine.showhive.home.profile

import android.os.Bundle
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.home.HomeActivity
import com.chahine.showhive.home.R
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_profile

    override fun setUpDependencyInjection() {
//        (requireActivity() as HomeActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }
}
