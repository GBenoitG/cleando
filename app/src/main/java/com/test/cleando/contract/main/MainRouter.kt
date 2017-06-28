package com.test.cleando.contract.main

import com.test.cleando.tool.Extra
import com.test.cleando.ui.fragment.main.DetailFragment
import com.test.cleando.ui.fragment.main.TaskFragment

/**
 * Created by Benoit on 20/06/2017.
 */
class MainRouter : MainContract.Router() {

    override fun goToDetail(taskId: Int) {

        val extras = hashMapOf<String, Any>()
        extras.put(Extra.TASK_ID, taskId)

        view.performFragmentTransaction(DetailFragment(), extras)

    }

    override fun goToList() {

        view.performFragmentTransaction(TaskFragment())

    }
}