package com.chahine.showhive.show

import com.chahine.showhive.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_show

    override fun setUpDependencyInjection() {
//        DaggerActivityComponent.builder()
//            .activity(this)
//            .activityModule(ActivityModule())
//            .appComponent(appComponent)
//            .build()
//            .inject(this)
    }
}
