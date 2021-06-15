package com.chahine.showhive.show

import android.os.Bundle
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.show.databinding.ActivityShowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
