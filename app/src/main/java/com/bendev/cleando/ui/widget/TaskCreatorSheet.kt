package com.bendev.cleando.ui.widget

import android.app.Dialog
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendev.cleando.R
import com.bendev.cleando.model.task.Status
import com.bendev.cleando.model.task.TaskModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.sheet_task_creator.*

class TaskCreatorSheet : BottomSheetDialogFragment(), View.OnClickListener {

    private var callback: TaskCreatorCallback? = null

    companion object {
        fun instantiate(callback: TaskCreatorCallback? = null): TaskCreatorSheet {
            val arguments = Bundle()
            arguments.putParcelable(EXTRA_LISTENER, callback)
            val sheet = TaskCreatorSheet()
            sheet.arguments = arguments
            return sheet
        }

        private val EXTRA_LISTENER = "listener"

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        this.callback = arguments?.getParcelable(EXTRA_LISTENER)

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sheet_task_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskCreator_confirm.setOnClickListener(this)
        taskCreator_cancel.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        dismiss()
        when (v) {
            taskCreator_confirm -> callback?.onConfirm(TaskModel(
                    taskCreator_name.text.toString(),
                    taskCreator_description.text.toString(),
                    Status.OPEN
            ))
            taskCreator_cancel -> callback?.onCancel()

        }

    }

    interface TaskCreatorCallback : Parcelable{

        fun onConfirm(task: TaskModel)

        fun onCancel()

        override fun writeToParcel(dest: Parcel?, flags: Int) {
        }

        override fun describeContents(): Int = 0
    }

}