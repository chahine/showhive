package com.chahine.showhive.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.chahine.showhive.auth.databinding.ActivitySplashBinding
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    @Inject lateinit var router: Router

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            connectBtn.setOnClickListener { viewModel.onConnectButtonClick() }
            skipBtn.setOnClickListener { viewModel.onSkipButtonClick() }
        }

        lifecycleScope.launchWhenCreated { viewModel.navigateToTrakt.collectLatest { router.connectWithTrakt() } }
        lifecycleScope.launchWhenCreated { viewModel.navigateToHome.collectLatest { router.home() } }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        viewModel.onNewIntent(intent)
    }
}
