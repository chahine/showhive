package com.chahine.showhive.home.calendar

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.base.Router
import com.chahine.showhive.home.HomeActivity
import com.chahine.showhive.home.R
import com.chahine.showhive.home.calendar.CalendarEvent.LoadCalendar
import com.chahine.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahine.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahine.showhive.home.calendar.CalendarModel.CalendarProgress
import com.chahine.showhive.home.util.DefaultSpacesItemDecoration
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_recycler_view.list
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

class CalendarFragment : BaseFragment() {

    @Inject lateinit var router: Router
    @Inject lateinit var adapter: CalendarAdapter
    @Inject lateinit var itemDecoration: DefaultSpacesItemDecoration
    @Inject lateinit var viewModel: CalendarViewModel

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

        viewModel.uiEvents.onNext(LoadCalendar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.data.observe(requireActivity()) { model -> onModelEvent(model!!) }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun onModelEvent(model: CalendarModel) {
        Timber.d("--> model: ${model.javaClass.simpleName}")
        when (model) {
            is CalendarProgress -> onCalendarProgress()
            is CalendarFailure -> onCalendarFailure(model)
            is CalendarCardSuccess -> onCalendarCardSuccess(model)
        }
    }

    private fun onCalendarProgress() {
        // TODO: implement progress state
    }

    private fun onCalendarFailure(model: CalendarFailure) {
        Timber.e(model.error)
    }

    private fun onCalendarCardSuccess(model: CalendarCardSuccess) {
        adapter.submitList(model.items)
        val today = LocalDate.now()
        list.scrollToPosition(
            model.items.indexOfFirst {
                it is DateHeaderItemView.Item && (it.dateTime.isAfter(today) || it.dateTime == today)
            }
        )
    }
}
