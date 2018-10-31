package com.bendev.cleando.ui.activity

import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import com.bendev.cleando.tool.router.Navigator
import com.bendev.cleando.ui.fragment.BaseFragment

/**
 * Created by Benoit on 20/06/2017.
 */
open class BaseActivity : AppCompatActivity(), Navigator.Router {

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

    override fun getRouterActivity(): AppCompatActivity = this

    override fun startActivity(intent: Intent, requestCode: Int?, options: ActivityOptionsCompat?) {

        if (requestCode != null) {

            if (options != null && Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                super.startActivityForResult(intent, requestCode, options.toBundle())
            } else {
                super.startActivityForResult(intent, requestCode)
            }

        } else {

            if (options != null && Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                super.startActivity(intent, options.toBundle())
            } else {
                super.startActivity(intent)
            }

        }

    }
}