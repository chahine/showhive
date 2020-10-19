package com.chahinem.showhive.home.calendar

sealed class CalendarModel {
    class CalendarProgress : CalendarModel()
    class CalendarCardSuccess(val items: List<CalendarAdapter.Item>) : CalendarModel()
    class CalendarFailure(val error: Throwable) : CalendarModel()
}
