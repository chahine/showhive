package com.chahinem.showhive.base.rv

interface RvItem {
  fun itemViewType(): Int = 0
  fun gridSpan(): Int = 1
}
