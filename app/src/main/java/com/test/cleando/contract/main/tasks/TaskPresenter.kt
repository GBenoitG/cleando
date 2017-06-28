package com.test.cleando.contract.main.tasks

import android.content.Context
import android.content.res.Resources
import com.test.cleando.model.task.TaskModel
import com.test.cleando.tool.Injector

//import javax.inject.Inject

class TaskPresenter : TaskContract.Presenter() {

    /*@Inject lateinit*/var context: Context = Injector.context
    var resources: Resources = Injector.resources

    override fun presentTasks(response: TaskContract.Task.Response) {

        output.showTasks(response.tasks.toViewModel())

    }

    override fun presentNoData(error: Int) {

        output.showErrorNoData(resources.getString(error))

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
