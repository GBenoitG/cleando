package com.bendev.cleando.contract.main.tasks

import com.bendev.cleando.model.task.TaskModel

class TaskPresenter : TaskContract.Presenter() {

    override fun presentTasks(response: TaskContract.Task.Response) {

        output.showTasks(response.tasks.toViewModel())

    }

    override fun presentNoData() {

        output.showErrorNoData()

    }

    private fun List<TaskModel>.toViewModel(): List<TaskContract.Task.ViewModel> {

        var viewModels = arrayListOf<TaskContract.Task.ViewModel>()

        forEach {
            viewModels.add(TaskContract.Task.ViewModel(
                    id = it.id,
                    name = it.title,
                    status = it.status)
            )
        }

        return viewModels
    }
}
