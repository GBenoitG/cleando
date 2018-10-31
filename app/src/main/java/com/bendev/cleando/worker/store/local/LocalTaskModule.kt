package com.bendev.cleando.worker.store.local

import com.bendev.cleando.model.task.TaskModel

/**
 * Created by Benoit on 21/06/2017.
 */
interface LocalTaskModule {

    fun addOrUpdateTask(taskModel: TaskModel)

    fun getTask(taskId: Int): TaskModel?

    fun getTasks(): List<TaskModel>

    fun getOpenedTasks(): List<TaskModel>

    fun getClosedTasks(): List<TaskModel>

    fun getMaxId(): Int

}