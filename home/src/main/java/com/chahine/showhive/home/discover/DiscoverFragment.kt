package com.chahine.showhive.home.discover

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.HomeActivity
import com.chahine.showhive.home.R
import com.chahine.showhive.home.util.DefaultSpacesItemDecoration
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_recycler_view.list
import timber.log.Timber
import javax.inject.Inject

class DiscoverFragment : BaseFragment() {

    @Inject lateinit var router: Router
    @Inject lateinit var adapter: DiscoverAdapter
    @Inject lateinit var itemDecoration: DefaultSpacesItemDecoration
    @Inject lateinit var viewModel: DiscoverViewModel

    override fun getLayoutId() = R.layout.fragment_recycler_view

    override fun setUpDependencyInjection() {
        (requireActivity() as HomeActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.addItemDecoration(itemDecoration)
        list.adapter = adapter

        viewModel.uiEvents.onNext(DiscoverEvent.LoadTrendingShows())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.data.observe(requireActivity()) { model -> onModelEvent(model!!) }
    }

    private fun onModelEvent(model: DiscoverModel) {
        Timber.d("--> model: ${model.javaClass.simpleName}")
        when (model) {
            is DiscoverModel.DiscoverIdle -> onDiscoverIdle()
            is DiscoverModel.DiscoverFailure -> onDiscoverFailure(model)
            is DiscoverModel.DiscoverSuccess -> onDiscoverSuccess(model)
        }
    }

    private fun onDiscoverIdle() {
        // TODO: render idle state
    }

    private fun onDiscoverFailure(model: DiscoverModel.DiscoverFailure) {
        Timber.e(model.error)
    }

    private fun onDiscoverSuccess(model: DiscoverModel.DiscoverSuccess) {
        adapter.submitList(model.items)
    }
}
