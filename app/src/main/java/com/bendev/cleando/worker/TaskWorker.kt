package com.bendev.cleando.worker

import com.bendev.cleando.contract.main.tasks.TaskContract
import com.bendev.cleando.model.task.Status
import com.bendev.cleando.model.task.TaskModel
import com.bendev.cleando.tool.Injector
import com.bendev.cleando.worker.store.local.LocalTaskModule

//import javax.inject.Inject

/**
 * Created by Benoit on 21/06/2017.
 */
object TaskWorker {

    var taskModule: LocalTaskModule = Injector.localStore.taskModule

    fun addTask(taskModel: TaskModel) {

        taskModule.addOrUpdateTask(taskModel)

    }

    fun updateTask(taskId: Int, taskModel: TaskModel) {

        taskModel.id = taskId

        taskModule.addOrUpdateTask(taskModel)

    }

    fun editTask(taskId: Int, isDone: Boolean) {

        val task = taskModule.getTask(taskId)

        if (task != null) {
            task.status = if (isDone) Status.CLOSE else Status.OPEN
            taskModule.addOrUpdateTask(task)
        }

    }

    fun getTask(taskId: Int): TaskModel? {
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