package com.chahine.showhive.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int
    abstract fun setUpDependencyInjection()

    protected val appComponent get() = (requireActivity().applicationContext as ShowHiveApp).component
    protected val fragmentTag get() = this::class.java.canonicalName

    override fun onAttach(context: Context) {
        setUpDependencyInjection()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(getLayoutId(), container, false)!!
}
