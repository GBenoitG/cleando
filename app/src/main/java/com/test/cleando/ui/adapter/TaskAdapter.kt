package com.test.cleando.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.test.cleando.contract.main.tasks.TaskContract
import com.test.cleando.ui.adapter.viewholder.TaskViewHolder

/**
 * Created by Benoit on 22/06/2017.
 */
class TaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = arrayListOf<TaskContract.Task.ViewModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var taskAdapterDelegate: TaskAdapterDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return TaskViewHolder(parent).listenTaskClick { taskId ->
            taskAdapterDelegate?.onTaskClick(taskId)
        }.listenCheckTask { taskId, status ->
            taskAdapterDelegate?.onCheckTask(taskId, status)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TaskViewHolder) {
            holder.bindTask(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setTasks(tasks: List<TaskContract.Task.ViewModel>) {

        data = arrayListOf()

        data.addAll(tasks)

    }

}