package com.test.cleando.ui.activity

import android.os.Bundle

import com.test.cleando.R
import com.test.cleando.contract.main.MainContract
import com.test.cleando.ui.fragment.main.DetailFragment
import com.test.cleando.ui.fragment.main.TaskFragment

class MainActivity : BaseActivity(), MainContract.Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToList()

    }

    override fun goToList() {

        showFragment(R.id.container, TaskFragment())

    }

    override fun goToDetail(taskId: Int) {

        showFragment(R.id.container, DetailFragment.instanciate(this, taskId))

    }

    override fun onBackPressed() {

        when (getCurrentFragment(R.id.container)) {
            TaskFragment() -> super.onBackPressed()
            DetailFragment() -> goToList()
        }

    }
}
