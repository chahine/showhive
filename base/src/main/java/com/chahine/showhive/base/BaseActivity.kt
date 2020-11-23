package com.chahine.showhive.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected val appComponent get() = (applicationContext as ShowHiveApp).component

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpDependencyInjection()
        super.onCreate(savedInstanceState)
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

    abstract fun setUpDependencyInjection()
}
