package com.test.cleando.contract.task

import android.content.Context
import com.test.cleando.R
import com.test.cleando.contract.main.tasks.TaskContract
import com.test.cleando.contract.main.tasks.TaskInteractor
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel
import com.test.cleando.tool.Injector
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Benoit on 28/06/2017.
 */
@RunWith(MockitoJUnitRunner::class)
class TaskInteractorTest {

    lateinit var interactor: TaskInteractor
    lateinit var presenterSpy: PresenterSpy

    @Mock lateinit var context: Context

    @Before
    fun setup() {

        Injector.configure(context)

        interactor = TaskInteractor()
        presenterSpy = PresenterSpy()

        interactor.output = presenterSpy

    }

    @After
    fun clear() {



    }

    @Test
    fun addTaskWithValidEntryShouldReturnNoError() {

        val taskModel = TaskModel(-1, "Name", "Description", Status.OPEN)

        interactor.addTask(TaskContract.Task.Request(taskModel))

        assertEquals(1, presenterSpy.response?.tasks?.size)

    }

    @Test
    fun addTaskWithExistIdShouldOverwriteItem() {

        val firstTask = TaskModel(0, "Name", "Description", Status.OPEN)
        val firstTaskBis = TaskModel(0, "NameBis", "DescriptionBis", Status.CLOSE)

        interactor.addTask(TaskContract.Task.Request(firstTask))

        assertEquals(firstTask.title, presenterSpy.response?.tasks?.filter { taskModel -> taskModel.id == 0 }!![0].title)

        interactor.addTask(TaskContract.Task.Request(firstTaskBis))

        assertEquals(firstTaskBis.title, presenterSpy.response?.tasks?.filter { taskModel -> taskModel.id == 0 }!![0].title)

    }

    @Test
    fun getTaskFilteredByAll() {

        interactor.addTask(TaskContract.Task.Request(TaskModel(-1, "BouiOpen", "DouiscriptionOpen", Status.OPEN)))
        interactor.addTask(TaskContract.Task.Request(TaskModel(-1, "BouiClose", "DouiscriptionClose", Status.CLOSE)))

        interactor.getTasks(TaskContract.Task.Request(TaskContract.Select.ALL))

        assertEquals(2, presenterSpy.response?.tasks?.size)

    }

    @Test
    fun getTaskFilteredByOpen() {

        interactor.addTask(TaskContract.Task.Request(TaskModel(-1, "BouiOpen", "BouiscriptionOpen", Status.OPEN)))
        interactor.addTask(TaskContract.Task.Request(TaskModel(-1, "BouiClose", "BouiscriptionClose", Status.CLOSE)))

        interactor.getTasks(TaskContract.Task.Request(TaskContract.Select.OPENED))

        assertEquals(1, presenterSpy.response?.tasks?.size)

    }

    @Test
    fun getTaskFilteredByClose() {

        interactor.addTask(TaskContract.Task.Request(TaskModel(-1, "BouiOpen", "BouiscriptionOpen", Status.OPEN)))
        interactor.addTask(TaskContract.Task.Request(TaskModel(-1, "BouiClose", "BouiscriptionClose", Status.CLOSE)))

        interactor.getTasks(TaskContract.Task.Request(TaskContract.Select.CLOSED))

        assertEquals(1, presenterSpy.response?.tasks?.size)

    }

    @Test
    fun getNoTaskShouldReturnError() {

        interactor.getTasks(TaskContract.Task.Request(TaskContract.Select.ALL))

        assertTrue(presenterSpy.response == null)
        assertEquals(R.string.nodata, presenterSpy.errorRes)

    }

    class PresenterSpy : TaskContract.Presenter() {

        var response: TaskContract.Task.Response? = null
        var errorRes: Int = 0

        override fun presentTasks(response: TaskContract.Task.Response) {
            this.response = response
        }

        override fun presentNoData(errorRes: Int) {
            this.errorRes = errorRes
        }
    }

}