package com.bendev.cleando.contract.task

import android.content.Context
import android.test.MoreAsserts
import com.bendev.cleando.R
import com.bendev.cleando.contract.main.tasks.TaskContract
import com.bendev.cleando.contract.main.tasks.TaskPresenter
import com.bendev.cleando.model.task.Status
import com.bendev.cleando.model.task.TaskModel
import com.bendev.cleando.tool.Injector
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Benoit on 28/06/2017.
 */
@RunWith(MockitoJUnitRunner::class)
class TaskPresenterTest {

    lateinit var presenter: TaskPresenter
    lateinit var controllerSpy: ControllerSpy

    @Mock lateinit var context: Context

    @Before
    fun setup() {

        Injector.configure(context)

        presenter = TaskPresenter()
        controllerSpy = ControllerSpy()

        presenter.output = controllerSpy

    }

    @Test
    fun presentNoData() {

        presenter.presentNoData(R.string.no_data_error)

        assertEquals(R.string.no_data_error, controllerSpy.errorRes)
        assertTrue(controllerSpy.tasks.isEmpty())

    }

    @Test
    fun presentValidData() {

        presenter.presentTasks(TaskContract.Task.Response(listOf(
                TaskModel(0, "ValidTitle", "ValidDesc", Status.OPEN),
                TaskModel(1, "ValidTitle1", "ValidDesc1", Status.CLOSE))))

        assertEquals(2, controllerSpy.tasks.size)
        assertEquals(0, controllerSpy.errorRes)

    }

    @Test
    fun presentEmptyListShouldReturnError() {

        presenter.presentTasks(TaskContract.Task.Response(listOf()))



    }

    class ControllerSpy : TaskContract.Controller {

        var errorRes: Int = 0
        var tasks = listOf<TaskContract.Task.ViewModel>()

        override fun showTasks(tasks: List<TaskContract.Task.ViewModel>) {
            this.tasks = tasks
        }

        override fun showErrorNoData(errorRes: Int) {
            this.errorRes = errorRes
        }

    }

}