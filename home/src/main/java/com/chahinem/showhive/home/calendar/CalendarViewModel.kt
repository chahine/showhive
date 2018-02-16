package com.chahinem.showhive.home.calendar

import android.arch.lifecycle.ViewModel
import com.chahinem.trakt.api.TraktApi
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    private val traktApi: TraktApi
) : ViewModel() {
  fun doSomething() {
  }
}