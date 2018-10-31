package com.bendev.cleando.contract.main.detail

import com.bendev.cleando.ui.fragment.main.DetailFragment

object DetailConfigurator {

    fun configure(view: DetailFragment) {

        val presenter = DetailPresenter()
        presenter.output = view

        val interactor = DetailInteractor()
        interactor.output = presenter

        view.output = interactor

    }

}
