package com.chahinem.showhive.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

abstract class BaseActivity : AppCompatActivity() {

  protected val appComponent get() = (applicationContext as ShowHiveApp).component

  @LayoutRes abstract fun getLayoutId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    setUpDependencyInjection()
    super.onCreate(savedInstanceState)
    setContentView(getLayoutId())
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        finish()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  open fun setUpDependencyInjection() {}
}
