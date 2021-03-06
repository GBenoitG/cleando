package com.bendev.cleando.ui.adapter.viewholder

import android.view.ViewGroup
import com.bendev.cleando.R
import com.bendev.cleando.contract.main.tasks.TaskContract
import com.bendev.cleando.model.task.Status
import com.bendev.cleando.ui.widget.TaskImageButton
import kotlinx.android.synthetic.main.adapter_task.view.*

/**
 * Created by Benoit on 22/06/2017.
 */
class TaskViewHolder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_task) {

    var task: TaskContract.Task.ViewModel? = null

    fun bindTask(task: TaskContract.Task.ViewModel) : TaskViewHolder{

        this.task = task

        itemView.title.text = task.name
        itemView.taskButton.status = when (task.status) {

            Status.OPEN -> TaskImageButton.Status.OPEN
            Status.CLOSE -> TaskImageButton.Status.CLOSE

        }

        return this
    }

    fun listenTaskClick(action: ((Int) -> Unit)): TaskViewHolder {

        itemView.setOnClickListener {

            if (task != null) {
                action.invoke(task!!.id)
            }

        }

        return this

    }

    fun listenCheckTask(action: ((Int, Status) -> Unit)): TaskViewHolder {

        itemView.taskButton.buttonListener = object : TaskImageButton.ButtonTaskListener {
            override fun onButtonTaskClick(status: TaskImageButton.Status) {

                if (task != null) {
                    action.invoke(task!!.id,
                            when (status) {
                                TaskImageButton.Status.OPEN -> Status.OPEN
                                else -> Status.CLOSE
                            })
                }

            }
        }

        return this

    }

}