package com.test.cleando.worker.store.local.realm

import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel
import com.test.cleando.worker.store.local.LocalTaskModule
import com.test.cleando.worker.store.local.realm.model.RealmTask
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults

/**
 * Created by Benoit on 21/06/2017.
 */
class DummyLocalTaskModule : LocalTaskModule {

    var tasks = arrayListOf<TaskModel>()

    override fun addOrUpdateTask(taskModel: TaskModel) {
        if (tasks.filter { task -> task.id == taskModel.id }.isEmpty()) {
            if (taskModel.id == -1) {
                taskModel.id = getMaxId() + 1
            }
            tasks.add(taskModel)
        } else {
            val index = tasks.indexOf(tasks.find { task -> task.id == taskModel.id })
            tasks[index] = taskModel
        }
    }

    override fun getTask(taskId: Int): TaskModel {
        return tasks.filter { taskModel -> taskModel.id == taskId }[0]
    }

    override fun getTasks(): List<TaskModel> {
        return tasks
    }

    override fun getOpenedTasks(): List<TaskModel> {
        return tasks.filter { taskModel -> taskModel.status == Status.OPEN }
    }

    override fun getClosedTasks(): List<TaskModel> {
        return tasks.filter { taskModel -> taskModel.status == Status.CLOSE }
    }

    override fun getMaxId(): Int {
        return tasks.maxBy { task -> task.id }?.id ?: 0
    }
}