package com.chahine.showhive.home.discover

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.FragmentRecyclerViewLoadingBinding
import com.chahine.showhive.home.util.DefaultSpacesItemDecoration
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DiscoverFragment : BaseFragment() {

    init {
        Timber.d("DiscoverFragment#${hashCode()}")
    }

    @Inject lateinit var router: Router
    @Inject lateinit var discoverAdapter: DiscoverAdapter
    @Inject lateinit var itemDecoration: DefaultSpacesItemDecoration

    private val viewModel: DiscoverViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_recycler_view_loading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRecyclerViewLoadingBinding.bind(view)

        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(itemDecoration)
            adapter = discoverAdapter
        }

        lifecycleScope.launch {
            viewModel.trending().collectLatest {
                binding.spinner.isVisible = false
                discoverAdapter.submitData(it)
            }
        }
    }
}
