package com.chahinem.showhive.show

import com.chahinem.showhive.base.BaseActivity

class ShowActivity : BaseActivity() {

  override fun getLayoutId() = R.layout.activity_show

  override fun setUpDependencyInjection() {
    DaggerActivityComponent.builder()
        .activity(this)
        .activityModule(ActivityModule())
        .appComponent(appComponent)
        .build()
        .inject(this)
  }
}
