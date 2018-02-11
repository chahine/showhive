package com.chahinem.showhive.home

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.trakt.api.TraktApi
import com.chahinem.trakt.entities.Extended.NO_SEASONS
import kotlinx.android.synthetic.main.activity_home.list
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

  @Inject lateinit var api: TraktApi
  @Inject lateinit var adapter: HomeAdapter

  override fun getLayoutId() = R.layout.activity_home

  override fun setUpDependencyInjection() {
    val component = DaggerActivityComponent.builder()
        .activityModule(ActivityModule())
        .appComponent(appComponent)
        .build()

    component.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    list.layoutManager = GridLayoutManager(this, 2)
    list.adapter = adapter
  }

  override fun onResume() {
    super.onResume()

    api.popular(1, 10, NO_SEASONS).subscribe({
      val data = mutableListOf<HomeAdapter.Item>()
      it.forEach { data.add(ShowItemView.Item(it)) }
      adapter.swapData(data)
    }, Timber::e)
  }
}
