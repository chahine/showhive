package com.chahine.showhive.home.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chahine.showhive.home.R
import javax.inject.Inject

class DefaultSpacesItemDecoration @Inject constructor(resources: Resources) : RecyclerView.ItemDecoration() {

    private val spacing: Int = resources.getDimensionPixelSize(R.dimen.spacing_standard)

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
