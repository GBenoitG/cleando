package com.test.cleando.ui.fragment.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.test.cleando.R
import com.test.cleando.contract.main.MainContract
import com.test.cleando.contract.main.tasks.TaskConfigurator
import com.test.cleando.contract.main.tasks.TaskContract
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel
import com.test.cleando.ui.adapter.TaskAdapter
import com.test.cleando.ui.adapter.TaskAdapterDelegate
import com.test.cleando.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*

/**
 * Created by Benoit on 20/06/2017.
 */
class TaskFragment : BaseFragment(), TaskContract.Controller, TaskAdapterDelegate {

    lateinit var output: TaskContract.Interactor
    lateinit var parentRouter: MainContract.Router

    private var taskAdater = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskConfigurator.configure(this)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView.layoutManager = LinearLayoutManager(activity)
        listView.adapter = taskAdater

        taskAdater.taskAdapterDelegate = this

        output.getTasks(TaskContract.Task.Request(TaskContract.Select.ALL))

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.add_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.app_bar_add -> generateTask()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun generateTask() {

        var task = TaskModel(0, "Title", "", Status.OPEN)

        output.addTask(TaskContract.Task.Request(-1, task))

    }

    override fun showTasks(tasks: List<TaskContract.Task.ViewModel>) {

        taskAdater.setTasks(tasks)

        errorLayout.visibility = View.GONE

    }

    override fun showErrorNoData(error: String) {

        listView.visibility = View.GONE

        errorLayout.visibility = View.VISIBLE

        textError.text = error

    }

    override fun onTaskClick(taskId: Int) {

        parentRouter.goToDetail(taskId)

    }

    override fun onCheckTask(taskId: Int, status: Status) {

        output.updateTask(TaskContract.Task.Request(taskId,
                when(status) {
                    Status.CLOSE -> false // CLOSE = isDone du coup on l'inverse pour le !isDone
                    else -> true
                }))

    }
}