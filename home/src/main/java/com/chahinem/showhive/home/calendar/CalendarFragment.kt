package com.chahinem.showhive.home.calendar

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chahinem.showhive.base.BaseFragment
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.home.HomeActivity
import com.chahinem.showhive.home.R
import com.chahinem.showhive.home.calendar.CalendarEvent.LoadCalendar
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarProgress
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_calendar.list
import timber.log.Timber

class CalendarFragment : BaseFragment() {

    @Inject lateinit var router: Router
    @Inject lateinit var adapter: CalendarAdapter
    @Inject lateinit var itemDecoration: CalendarItemDecoration
    @Inject lateinit var viewModel: CalendarViewModel

    override fun getLayoutId() = R.layout.fragment_calendar

    override fun setUpDependencyInjection() {
        (requireActivity() as HomeActivity).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.addItemDecoration(itemDecoration)
        list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.data.observe(requireActivity(), {
            if (it != null) {
                onModelEvent(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.uiEvents.onNext(LoadCalendar)
    }

    private fun onModelEvent(model: CalendarModel) {
        Timber.d("--> model: ${model.javaClass.simpleName}")
        when (model) {
            is CalendarProgress -> onCalendarProgress(model)
            is CalendarFailure -> onCalendarFailure(model)
            is CalendarCardSuccess -> onCalendarCardSuccess(model)
        }
    }

    private fun onCalendarProgress(model: CalendarProgress) {
        // TODO: implement progress state
    }

    private fun onCalendarFailure(model: CalendarFailure) {
        Timber.e(model.error)
    }

    private fun onCalendarCardSuccess(model: CalendarCardSuccess) {
        adapter.swapData(model.items)
        val now = ZonedDateTime.now()
        Handler().post {
            list.scrollToPosition(model.items.indexOfFirst {
                it is EpisodeItemView.Item && it.entry.firstAired.isAfter(now) ||
                        it is DateHeaderItemView.Item && (it.dateTime.isAfter(now.toLocalDate()))
            })
        }
    }
}
