package com.test.cleando.contract.task

import android.content.Context
import com.test.cleando.contract.main.MainContract
import com.test.cleando.contract.main.tasks.TaskConfigurator
import com.test.cleando.contract.main.tasks.TaskContract
import com.test.cleando.contract.main.tasks.TaskInteractor
import com.test.cleando.contract.main.tasks.TaskPresenter
import com.test.cleando.tool.Injector
import com.test.cleando.ui.fragment.main.TaskFragment
import org.junit.Assert.assertNotNull
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
class TaskConfiguratorTest {

    lateinit var controllerSpy: ControllerSpy

    @Mock lateinit var context: Context

    @Before
    fun setup() {

        Injector.configure(context)

        controllerSpy = ControllerSpy()
        TaskConfigurator.configure(controllerSpy)

    }

    @Test
    fun testContract() {
        val contract: TaskContract = TaskContract()
        assertNotNull(contract)
    }

    @Test
    fun testInteractor() {
        assertTrue(controllerSpy.output is TaskInteractor)
    }

    @Test
    fun testPresenter() {
        assertTrue(controllerSpy.output.output is TaskPresenter)
    }

    @Test
    fun testController() {
        assertTrue(controllerSpy is TaskContract.Controller)
    }

//    @Test
//    fun testRouter() {
//        assertTrue(controllerSpy.parentRouter is MainContract.Router)
//    }

    class ControllerSpy : TaskFragment()

}