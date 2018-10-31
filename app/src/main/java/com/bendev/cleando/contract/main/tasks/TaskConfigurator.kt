package com.bendev.cleando.contract.main.tasks

import com.bendev.cleando.ui.fragment.main.TaskFragment

object TaskConfigurator {

    fun configure(view: TaskFragment) {

        val presenter = TaskPresenter()
        presenter.output = view

        val interactor = TaskInteractor()
        interactor.output = presenter

        view.output = interactor

    }

}
