package com.test.cleando.ui.fragment.main

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import com.test.cleando.R
import com.test.cleando.contract.main.MainContract
import com.test.cleando.contract.main.detail.DetailConfigurator
import com.test.cleando.contract.main.detail.DetailContract
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel
import com.test.cleando.tool.Extra
import com.test.cleando.ui.fragment.BaseFragment
import com.test.cleando.ui.widget.TaskImageButton
import kotlinx.android.synthetic.main.adapter_task.*
import kotlinx.android.synthetic.main.framgent_detail.*

/**
 * Created by Benoit on 20/06/2017.
 */
class DetailFragment : BaseFragment(), DetailContract.Controller, View.OnFocusChangeListener, View.OnClickListener, TaskImageButton.ButtonTaskListener {

    lateinit var output: DetailContract.Interactor
    lateinit var parentNavigator: MainContract.Navigation

    lateinit var menu: Menu

    var taskId: Int = 0

    companion object {

        fun instanciate(parentNavigator: MainContract.Navigation, taskId: Int): DetailFragment {

            val fragment = DetailFragment()
            fragment.parentNavigator = parentNavigator
            fragment.taskId = taskId
            return fragment

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailConfigurator.configure(this)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.framgent_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        output.getTask(DetailContract.Task.Request(taskId))

        checkButton.buttonListener = this

        detailTitle.setOnClickListener(this)
        detailDescription.setOnClickListener(this)

        detailTitleEdit.onFocusChangeListener = this
        detailDescriptionEdit.onFocusChangeListener = this

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.detail_menu, menu)
        if (menu != null) {
            this.menu = menu
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> parentNavigator.goToList()
            R.id.app_bar_edit -> {
                if (item.title == Extra.SAVE) {
                    setOptionButtonEdit()
                    saveChanges()
                } else {
                    setOptionButtonSave()
                    switcherDesc.showNext()
                    detailDescriptionEdit.requestFocus()
                }
            }
        }

        return true
    }

    private fun setOptionButtonEdit() {
        setOptionButton(R.id.app_bar_edit, Extra.EDIT, R.drawable.ic_pencil)
    }

    private fun setOptionButtonSave() {
        setOptionButton(R.id.app_bar_edit, Extra.SAVE, R.drawable.ic_save)
    }

    private fun saveChanges() {
        exitAllEditor()

        val title = detailTitle.text.toString()
        val description = detailDescription.text.toString()
        val status = when (checkButton.status) {
            TaskImageButton.Status.OPEN -> Status.OPEN
            else -> Status.CLOSE
        }

        output.saveTask(DetailContract.Task.Request(TaskModel(taskId, title, description, status)))
    }

    private fun setOptionButton(itemId: Int, title: String, drawableRes: Int) {
        val item = menu.findItem(itemId)
        item.icon = resources.getDrawable(drawableRes)
        item.title = title
    }

    private fun exitDescriptionEditor() {
        detailDescription.text = detailDescriptionEdit.text.toString()
        if (switcherDesc.nextView.id == R.id.detailDescription) {
            switcherDesc.showNext()
        }
    }

    private fun exitTitleEditor() {
        detailTitle.text = detailTitleEdit.text.toString()
        if (switcherTitle.nextView.id == R.id.detailTitle) {
            switcherTitle.showNext()
        }
    }

    private fun exitAllEditor() {
        exitTitleEditor()
        exitDescriptionEditor()
    }

    override fun showTaskInfo(viewModel: DetailContract.Task.ViewModel) {

        detailTitle.text = viewModel.title
        detailTitleEdit.setText(viewModel.title, TextView.BufferType.EDITABLE)
        detailDescription.text = viewModel.description
        detailDescriptionEdit.setText(viewModel.description, TextView.BufferType.EDITABLE)
        checkButton.status = when (viewModel.status) {
            Status.CLOSE -> TaskImageButton.Status.CLOSE
            Status.OPEN -> TaskImageButton.Status.OPEN
        }

    }

    override fun showLoadingError() {
        Dialog(activity).title
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.detailTitle -> {
                switcherTitle.showNext()
                detailTitleEdit.requestFocus()
            }
            R.id.detailDescription -> {
                switcherDesc.showNext()
                detailDescriptionEdit.requestFocus()
            }
        }

        setOptionButtonSave()

    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            when (v?.id) {
                R.id.detailTitleEdit -> {
                    exitTitleEditor()
                }
                R.id.detailDescriptionEdit -> {
                    exitDescriptionEditor()
                }
            }
        }
    }

    override fun onButtonTaskClick(status: TaskImageButton.Status) {
        setOptionButtonSave()
        checkButton.status = if (status == TaskImageButton.Status.CLOSE)
            TaskImageButton.Status.OPEN else TaskImageButton.Status.CLOSE
    }
}