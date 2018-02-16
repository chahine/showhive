package com.chahinem.showhive.home.calendar

import com.chahinem.showhive.base.BaseFragment
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.home.HomeActivity
import com.chahinem.showhive.home.R
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

  override fun onResume() {
    super.onResume()

    viewModel.doSomething()
  }
}
