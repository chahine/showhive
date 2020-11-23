package com.chahine.showhive.show

import android.os.Bundle
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.show.databinding.ActivityShowBinding

class ShowActivity : BaseActivity() {

    override fun setUpDependencyInjection() {
        DaggerActivityComponent.builder()
            .activity(this)
            .activityModule(ActivityModule())
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
