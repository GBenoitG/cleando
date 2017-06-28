package com.test.cleando.contract.main

import com.test.cleando.ui.activity.MainActivity

object MainConfigurator {

    fun configure(view: MainActivity) {

        val presenter = MainPresenter()
        presenter.output = view

        val interactor = MainInteractor()
        interactor.output = presenter

        view.output = interactor

        val router = MainRouter()
        router.view = view

        view.router = router

    }

}
