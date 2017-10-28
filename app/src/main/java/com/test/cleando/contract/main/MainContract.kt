package com.test.cleando.contract.main

import com.test.cleando.contract.BaseController
import com.test.cleando.contract.BaseInteractor
import com.test.cleando.contract.BasePresenter
import com.test.cleando.contract.BaseRouter
import com.test.cleando.ui.activity.MainActivity

class MainContract {

    interface Navigation {

        fun goToDetail(taskId: Int)
        fun goToList()

    }

}
