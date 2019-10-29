package com.bendev.cleando.ui.adapter

import android.view.ViewGroup
import com.bendev.cleando.contract.main.tasks.TaskContract
import com.bendev.cleando.ui.adapter.viewholder.TaskViewHolder
import androidx.recyclerview.widget.RecyclerView
import com.bendev.cleando.R
import com.bendev.cleando.ui.adapter.viewholder.BaseViewHolder

/**
 * Created by Benoit on 22/06/2017.
 */
class TaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = arrayListOf<TaskContract.Task.ViewModel>()

    var taskAdapterDelegate: TaskAdapterDelegate? = null

    var state: State = State.LOADING

    private val TYPE_HOLDER = 0
    private val TYPE_EMPTY = 1
    private val TYPE_TASK = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TASK -> TaskViewHolder(parent)
            else -> object : BaseViewHolder(parent, R.layout.adapter_empty) {}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TaskViewHolder) {
            holder.bindTask(data[position]).listenTaskClick { taskId ->
                taskAdapterDelegate?.onTaskClick(taskId)
            }.listenCheckTask { taskId, status ->
                taskAdapterDelegate?.onCheckTask(taskId, status)
            }
        }

    }

    override fun getItemViewType(position: Int): Int = when (state) {
        State.LOADING -> TYPE_HOLDER
        State.CONTENT -> if (data.size == 0) TYPE_EMPTY else TYPE_TASK
    }

    override fun getItemCount(): Int = when (state) {
        State.LOADING -> 4
        State.CONTENT -> if (data.size == 0) 1 else data.size
    }

    fun setTasks(tasks: List<TaskContract.Task.ViewModel>) {

        val previousCount = itemCount
        val previousState = state

        data = arrayListOf()

        data.addAll(tasks)

        state = State.CONTENT

        when (previousState) {
            State.LOADING -> notifyDataSetChanged()
            State.CONTENT -> if (previousCount == data.size) {
                notifyDataSetChanged()
            } else {
                notifyItemRangeInserted(previousCount, data.size - previousCount)
            }
        }

    }

    fun setNoData() {

        data = arrayListOf()

        state = State.CONTENT

        notifyDataSetChanged()
    }

    enum class State {
        LOADING,
        CONTENT
    }

}