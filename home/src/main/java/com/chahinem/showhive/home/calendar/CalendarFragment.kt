package com.chahinem.showhive.home.calendar

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chahinem.showhive.base.BaseFragment
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.home.HomeActivity
import com.chahinem.showhive.home.R
import com.chahinem.showhive.home.calendar.CalendarEvent.LoadCalendar
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarProgress
import kotlinx.android.synthetic.main.fragment_calendar.list
import org.threeten.bp.ZonedDateTime
import timber.log.Timber
import javax.inject.Inject

class CalendarFragment : BaseFragment() {

  @Inject lateinit var router: Router
  @Inject lateinit var adapter: CalendarAdapter
  @Inject lateinit var itemDecoration: CalendarItemDecoration
  @Inject lateinit var viewModel: CalendarViewModel

  override fun getLayoutId() = R.layout.fragment_calendar

  override fun setUpDependencyInjection() {
    if (activity is HomeActivity) {
      (activity as HomeActivity).component.inject(this)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    list.layoutManager = LinearLayoutManager(context)
    list.addItemDecoration(itemDecoration)
    list.adapter = adapter
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.data.observe(this, Observer {
      if (it != null) {
        onModelEvent(it)
      }
    })
  }

  override fun onResume() {
    super.onResume()

    viewModel.uiEvents.onNext(LoadCalendar())
  }

  private fun onModelEvent(model: CalendarModel) {
    Timber.d("--> model: ${model.javaClass.simpleName}")
    when (model) {
      is CalendarProgress -> onCalendarProgress(model)
      is CalendarFailure -> onCalendarFailure(model)
      is CalendarCardSuccess -> onCalendarCardSuccess(model)
    }
  }

  private fun onCalendarProgress(model: CalendarProgress) {}

  private fun onCalendarFailure(model: CalendarFailure) {
    Timber.e(model.error)
  }

  private fun onCalendarCardSuccess(model: CalendarCardSuccess) {
    adapter.swapData(model.items)
    val now = ZonedDateTime.now()
    Handler().post {
      list.scrollToPosition(model.items.indexOfFirst {
        it is EpisodeItemView.Item && (it.entry.firstAired?.isAfter(now) ?: false)
            || it is DateHeaderItemView.Item && (it.dateTime.isAfter(now.toLocalDate()))
      })
    }
  }
}
