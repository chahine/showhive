package com.chahinem.showhive.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

  abstract fun getLayoutId(): Int

  protected val fragmentTag get() = this::class.java.canonicalName

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?) = inflater.inflate(getLayoutId(), container, false)!!
}
