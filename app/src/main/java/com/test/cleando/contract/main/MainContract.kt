package com.test.cleando.contract.main

import com.test.cleando.contract.BaseController
import com.test.cleando.contract.BaseInteractor
import com.test.cleando.contract.BasePresenter
import com.test.cleando.contract.BaseRouter
import com.test.cleando.ui.activity.MainActivity

class MainContract {

    abstract class Interactor : BaseInteractor<Presenter>() {

    }

    abstract class Presenter : BasePresenter<Controller>() {

    }

    interface Controller : BaseController {

    }

    abstract class Router : BaseRouter<MainActivity>() {

        abstract fun goToDetail(taskId: Int)

        abstract fun goToList()
    }

}
