package com.bendev.cleando.worker.store.local.realm

import com.bendev.cleando.model.task.Status
import com.bendev.cleando.model.task.TaskModel
import com.bendev.cleando.worker.store.local.LocalTaskModule
import com.bendev.cleando.worker.store.local.realm.model.RealmTask
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.Sort

/**
 * Created by Benoit on 21/06/2017.
 */
class RealmLocalTaskModule : LocalTaskModule {

    val realm: Realm
        get() = Realm.getDefaultInstance()

    val taskClass = RealmTask::class.java

    val query: RealmQuery<RealmTask>
        get() = realm.where(taskClass)

    override fun addOrUpdateTask(taskModel: TaskModel) {
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(taskModel.toRealm())
        }
    }

    override fun getTask(taskId: Int): TaskModel? {
        return query.equalTo(RealmTask.ID, taskId).findFirst()?.fromRealm()
    }

    override fun getTasks(): List<TaskModel> {
        return query.sort(RealmTask.ID, Sort.DESCENDING).findAll().fromRealm()
    }

    override fun getOpenedTasks(): List<TaskModel> {
        return query.equalTo(RealmTask.IS_DONE, false).sort(RealmTask.ID, Sort.DESCENDING).findAll().fromRealm()
    }

    override fun getClosedTasks(): List<TaskModel> {
        return query.equalTo(RealmTask.IS_DONE, true).sort(RealmTask.ID, Sort.DESCENDING).findAll().fromRealm()
    }

    override fun getMaxId(): Int {
        return query.max(RealmTask.ID)?.toInt() ?: 0
    }

    private fun TaskModel.toRealm(): RealmTask {
        var realmTask = RealmTask()

        realmTask.id = if (id == -1) getMaxId() + 1 else id
        realmTask.title = title
        realmTask.description = description
        realmTask.isDone = when (status) {
            Status.OPEN -> false
            else -> true
        }

        return realmTask
    }

    private fun RealmTask.fromRealm(): TaskModel {

        var model = TaskModel(
                id,
                title,
                description,
                if (isDone) Status.CLOSE else Status.OPEN)

        return model
    }

    private fun RealmResults<RealmTask>.fromRealm(): List<TaskModel> {

        var models = arrayListOf<TaskModel>()

        forEach {
            models.add(TaskModel(id = it.id,
                    title = it.title,
                    description = it.description,
                    status = if(it.isDone) Status.CLOSE
                             else Status.OPEN)
            )
        }

        return models
    }

}