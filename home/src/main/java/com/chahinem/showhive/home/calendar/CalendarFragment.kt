package com.chahinem.showhive.home.calendar

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.chahinem.showhive.base.BaseFragment
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.home.HomeActivity
import com.chahinem.showhive.home.R
import com.chahinem.showhive.home.calendar.CalendarEvent.LoadCalendar
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarProgress
import javax.inject.Inject

class CalendarFragment : BaseFragment() {

  @Inject lateinit var router: Router
  @Inject lateinit var viewModel: CalendarViewModel

  override fun getLayoutId() = R.layout.fragment_calendar

  override fun setUpDependencyInjection() {
    if (activity is HomeActivity) {
      (activity as HomeActivity).component.inject(this)
    }
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
    when (model) {
      is CalendarProgress -> onCalendarProgress(model)
      is CalendarFailure -> onCalendarFailure(model)
      is CalendarCardSuccess -> onCalendarCardSuccess(model)
    }
  }

  private fun onCalendarProgress(model: CalendarProgress) {}

  private fun onCalendarFailure(model: CalendarFailure) {}

  private fun onCalendarCardSuccess(model: CalendarCardSuccess) {}
}
