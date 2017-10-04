package com.test.cleando.ui.activity

import android.support.v7.app.AppCompatActivity
import com.test.cleando.ui.fragment.BaseFragment

/**
 * Created by Benoit on 20/06/2017.
 */
open class BaseActivity : AppCompatActivity() {

    protected fun showFragment(containerId: Int, fragment: BaseFragment) {

        val transaction = supportFragmentManager.beginTransaction()

        if (getCurrentFragment(containerId) != null) {
            transaction.replace(containerId, fragment)
        } else {
            transaction.add(containerId, fragment)
        }

        transaction.commit()

    }

    protected fun getCurrentFragment(containerId: Int): BaseFragment? {

        val currentFragment = supportFragmentManager.findFragmentById(containerId)

        if (currentFragment != null && currentFragment is BaseFragment) {
            return currentFragment
        } else {
            return null
        }

    }

    open fun performFragmentTransaction(fragment: BaseFragment) {}

    open fun performFragmentTransaction(fragment: BaseFragment, extra: Map<String, Any>? = null) {}
}