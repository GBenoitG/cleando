package com.test.cleando.worker.store.local

import com.test.cleando.worker.store.Store

//import javax.inject.Inject

/**
 * Created by Benoit on 21/06/2017.
 */
open class LocalStore : Store /*@Inject constructor(var context: Context)*/ {

    lateinit var taskModule: LocalTaskModule

}