package com.test.cleando.contract.main.tasks

import com.test.cleando.contract.BaseController
import com.test.cleando.contract.BaseInteractor
import com.test.cleando.contract.BasePresenter
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel

class TaskContract {

    class Task {

        class Request {
            var select: Select = Select.ALL

            constructor(select: Select) {
                this.select = select
            }

            var taskId: Int = 0
            var isDone: Boolean = false

            constructor(taskId: Int, isDone: Boolean) {
                this.taskId = taskId
                this.isDone = isDone
            }

            lateinit var taskModel: TaskModel

            constructor(taskId: Int = -1, taskModel: TaskModel) {
                this.taskId = taskId
                this.taskModel = taskModel
            }
        }

        class Response(var tasks: List<TaskModel>)

        class ViewModel(val id: Int,
                        val name: String,
                        val status: Status)
    }

    abstract class Interactor : BaseInteractor<Presenter>() {

        lateinit var tasks: List<TaskModel>

        abstract fun addTask(request: Task.Request)

        abstract fun getTasks(request: Task.Request)
        abstract fun updateTask(request: Task.Request)

    }

    abstract class Presenter : BasePresenter<Controller>() {

        abstract fun presentTasks(response: Task.Response)
        abstract fun presentNoData(error: Int)

    }

    interface Controller : BaseController {

        fun showTasks(tasks: List<Task.ViewModel>)
        fun showErrorNoData(error: String)

    }

    enum class Select {
        OPENED, CLOSED, ALL
    }

}
