package com.test.cleando.ui.adapter

import com.test.cleando.model.task.Status

/**
 * Created by Benoit on 26/06/2017.
 */
interface TaskAdapterDelegate {

    fun onTaskClick(taskId: Int)
    fun onCheckTask(taskId: Int, status: Status)

}