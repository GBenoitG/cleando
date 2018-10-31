package com.bendev.cleando.contract.main.detail

import com.bendev.cleando.worker.TaskWorker

class DetailInteractor : DetailContract.Interactor() {

    override fun getTask(request: DetailContract.Task.Request) {

        val task = TaskWorker.getTask(request.id)

        if (task != null) {
            output.presentTask(DetailContract.Task.Response(task))
        } else {
            output.presentLoadingError()
        }

    }

    override fun saveTask(request: DetailContract.Task.Request) {

        val task = request.task

        TaskWorker.updateTask(task.id, task)

        getTask(DetailContract.Task.Request(task.id))

    }
}
