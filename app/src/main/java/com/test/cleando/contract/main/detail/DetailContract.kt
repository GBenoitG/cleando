package com.test.cleando.contract.main.detail

import com.test.cleando.contract.BaseController
import com.test.cleando.contract.BaseInteractor
import com.test.cleando.contract.BasePresenter
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel

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
