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
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_recycler_view.list
import javax.inject.Inject

class DiscoverFragment : BaseFragment() {

    @Inject lateinit var router: Router
    @Inject lateinit var adapter: DiscoverAdapter
    @Inject lateinit var itemDecoration: DefaultSpacesItemDecoration
    @Inject lateinit var viewModel: DiscoverViewModel

    private var disposable: Disposable? = null

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

        disposable = viewModel.trending().subscribe { data -> adapter.submitData(lifecycle, data) }
    }

    override fun onStop() {
        disposable?.dispose()
        disposable = null
        super.onStop()
    }
}
