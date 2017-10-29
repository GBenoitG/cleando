package com.test.cleando.contract.main.tasks

import com.test.cleando.R
import com.test.cleando.model.task.TaskModel
import com.test.cleando.worker.TaskWorker
import java.util.*

class TaskInteractor : TaskContract.Interactor() {

    override fun addTask(request: TaskContract.Task.Request) {

        val task = request.taskModel

        TaskWorker.addTask(task)

        getTasks(TaskContract.Task.Request(TaskContract.Select.ALL))

    }

    override fun getTasks(request: TaskContract.Task.Request) {

        val select = request.select

        val tasks: List<TaskModel> = TaskWorker.getTasks(select)

        if (!tasks.isEmpty()) {
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
