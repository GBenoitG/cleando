package com.bendev.cleando.contract.main.detail

import com.bendev.cleando.contract.BaseController
import com.bendev.cleando.contract.BaseInteractor
import com.bendev.cleando.contract.BasePresenter
import com.bendev.cleando.model.task.Status
import com.bendev.cleando.model.task.TaskModel

class DetailContract {

    class Task {

        class Request {
            var id: Int = 0

            constructor(id: Int) {
                this.id = id
            }

            lateinit var task: TaskModel

            constructor(task: TaskModel) {
                this.task = task
            }
        }

        class Response(val task: TaskModel)

        class ViewModel(val id: Int,
                        val title: String,
                        val description: String,
                        val status: Status)

    }

    abstract class Interactor : BaseInteractor<Presenter>() {

        abstract fun getTask(request: Task.Request)
        abstract fun saveTask(request: Task.Request)

    }

    abstract class Presenter : BasePresenter<Controller>() {

        abstract fun presentTask(response: Task.Response)
        abstract fun presentLoadingError()

    }

    interface Controller : BaseController {

        fun showTaskInfo(viewModel: Task.ViewModel)
        fun showLoadingError()

    }

}
