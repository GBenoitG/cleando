package com.test.cleando.worker

import com.test.cleando.contract.main.tasks.TaskContract
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel
import com.test.cleando.tool.Injector
import com.test.cleando.worker.store.local.LocalTaskModule

//import javax.inject.Inject

/**
 * Created by Benoit on 21/06/2017.
 */
object TaskWorker {

    var taskModule: LocalTaskModule = Injector.localStore.taskModule

    fun editTask(taskId: Int, taskModel: TaskModel) {

        var id = 0
        if (taskId == -1) {
            id = taskModule.getMaxId() + 1
            taskModel.title = taskModel.title + " " + id
        } else {
            id = taskId
        }

        taskModel.id = id

        taskModule.addOrUpdateTask(taskModel)

    }

    fun editTask(taskId: Int, isDone: Boolean) {

        val task = taskModule.getTask(taskId)

        task.status = if (isDone) Status.CLOSE else Status.OPEN

        taskModule.addOrUpdateTask(task)

    }

    fun getTask(taskId: Int): TaskModel {
        return taskModule.getTask(taskId)
    }

    fun getTasks(selec: TaskContract.Select): List<TaskModel> {
        return when (selec) {
            TaskContract.Select.CLOSED -> taskModule.getClosedTasks()
            TaskContract.Select.OPENED -> taskModule.getOpenedTasks()
            else -> taskModule.getTasks()
        }
    }

}