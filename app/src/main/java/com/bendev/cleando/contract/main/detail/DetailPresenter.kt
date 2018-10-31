package com.bendev.cleando.contract.main.detail

import com.bendev.cleando.model.task.TaskModel

class DetailPresenter : DetailContract.Presenter() {

    override fun presentTask(response: DetailContract.Task.Response) {

        val viewModel = response.task.toViewModel()

        output.showTaskInfo(viewModel)
    }

    override fun presentLoadingError() {
        output.showLoadingError()
    }


    private fun TaskModel.toViewModel(): DetailContract.Task.ViewModel {

        return DetailContract.Task.ViewModel(id, title, description, status)

    }

}
