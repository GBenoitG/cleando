package com.bendev.cleando.ui.activity

import android.os.Bundle

import com.bendev.cleando.R
import com.bendev.cleando.contract.main.MainContract
import com.bendev.cleando.ui.fragment.main.DetailFragment
import com.bendev.cleando.ui.fragment.main.TaskFragment

class MainActivity : BaseActivity(), MainContract.Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToList()

    }

    override fun goToList() {

        showFragment(R.id.container, TaskFragment.instanciate(this))

    }

    override fun goToDetail(taskId: Int) {

        showFragment(R.id.container, DetailFragment.instanciate(this, taskId))

    }

    override fun onBackPressed() {

        if (getCurrentFragment(R.id.container) is DetailFragment) {
            goToList()
        } else {
            super.onBackPressed()
        }

    }
}
