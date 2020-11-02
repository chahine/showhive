package com.chahine.showhive.base.rv

interface RvItem {
    fun itemViewType(): Int
    fun gridSpan(): Int = 1
}
