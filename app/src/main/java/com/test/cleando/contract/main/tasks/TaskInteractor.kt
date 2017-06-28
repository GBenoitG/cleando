package com.test.cleando.contract.main.tasks

import com.test.cleando.R
import com.test.cleando.worker.TaskWorker

class TaskInteractor : TaskContract.Interactor() {

    init {
        tasks = listOf()
    }

    override fun addTask(request: TaskContract.Task.Request) {

        val taskId = request.taskId
        val task = request.taskModel

        TaskWorker.editTask(taskId, task)

        getTasks(TaskContract.Task.Request(TaskContract.Select.ALL))

    }

    override fun getTasks(request: TaskContract.Task.Request) {

        val select = request.select

        tasks = TaskWorker.getTasks(select)

        howToPresent()

    }

    private fun howToPresent() {
        if (tasks.isNotEmpty()) {
            output.presentTasks(TaskContract.Task.Response(tasks))
        } else {
            output.presentNoData(R.string.nodata)
        }
    }

    override fun updateTask(request: TaskContract.Task.Request) {

        val taskId = request.taskId
        val isDone = request.isDone

        TaskWorker.editTask(taskId, isDone)

        getTasks(TaskContract.Task.Request(TaskContract.Select.ALL))

    }

}
