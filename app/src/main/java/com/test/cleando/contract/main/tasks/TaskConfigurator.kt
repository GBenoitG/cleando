package com.test.cleando.contract.main.tasks

import com.test.cleando.ui.fragment.main.TaskFragment

object TaskConfigurator {

    fun configure(view: TaskFragment) {

        val presenter = TaskPresenter()
        presenter.output = view

        val interactor = TaskInteractor()
        interactor.output = presenter

        view.output = interactor

    }

}
