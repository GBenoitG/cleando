package com.test.cleando.contract.task

import android.content.Context
import com.test.cleando.contract.main.tasks.TaskContract
import com.test.cleando.contract.main.tasks.TaskInteractor
import com.test.cleando.model.task.Status
import com.test.cleando.model.task.TaskModel
import com.test.cleando.tool.Injector
import com.test.cleando.worker.TaskWorker
import mockit.MockUp
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

    @Test
    fun addTaskWithValidEntryShouldReturnNoError() {

        val taskModel = TaskModel(0, "Name", "Description", Status.OPEN)

        interactor.addTask(TaskContract.Task.Request(taskModel))

        assertEquals(1, presenterSpy.response.tasks.size)

    }

    @Test
    fun addTaskWithExistIdShouldReturn

    class PresenterSpy : TaskContract.Presenter() {

        lateinit var response: TaskContract.Task.Response
        var errorRes: Int = 0

        override fun presentTasks(response: TaskContract.Task.Response) {
            this.response = response
        }

        override fun presentNoData(errorRes: Int) {
            this.errorRes = errorRes
        }
    }

}