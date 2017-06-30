package com.test.cleando.worker

import android.content.Context
import com.test.cleando.tool.Injector
import org.junit.Before
import org.mockito.Mock

/**
 * Created by Benoit on 29/06/2017.
 */
class TaskWorkerTest {

    @Mock lateinit var context: Context

    @Before
    fun setup() {

        Injector.configure(context)



    }

}