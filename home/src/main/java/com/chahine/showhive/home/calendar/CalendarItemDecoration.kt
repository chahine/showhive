package com.chahine.showhive.home.calendar

import android.app.Activity
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class CalendarItemDecoration @Inject constructor(activity: Activity) :
    RecyclerView.ItemDecoration() {

    private val spacing: Int =
        activity.resources.getDimensionPixelSize(com.chahine.showhive.base.R.dimen.spacing_standard)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter!!.itemCount
        when (position) {
            0 -> outRect.set(spacing, spacing, spacing, spacing / 2)
            count - 1 -> outRect.set(spacing, spacing / 2, spacing, spacing)
            else -> outRect.set(spacing, spacing / 2, spacing, spacing / 2)
        }
    }
}
