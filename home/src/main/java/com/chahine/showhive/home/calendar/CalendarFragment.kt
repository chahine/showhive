package com.chahine.showhive.home.calendar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.FragmentRecyclerViewBinding
import com.chahine.showhive.home.util.DefaultSpacesItemDecoration
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class CalendarFragment : BaseFragment() {

    init {
        Timber.d("CalendarFragment#${hashCode()}")
    }

    @Inject lateinit var router: Router
    @Inject lateinit var adapter: CalendarAdapter
    @Inject lateinit var itemDecoration: DefaultSpacesItemDecoration

    private val viewModel: CalendarViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_recycler_view

    override fun setUpDependencyInjection() {
//        (requireActivity() as HomeActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRecyclerViewBinding.bind(view)

        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.addItemDecoration(itemDecoration)
        binding.list.adapter = adapter

        lifecycleScope.launch {
            viewModel.myCalendar().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
