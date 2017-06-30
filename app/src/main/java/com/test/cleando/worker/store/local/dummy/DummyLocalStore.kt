package com.test.cleando.worker.store.local.realm

import android.content.Context
import com.test.cleando.tool.Injector
import com.test.cleando.worker.store.local.LocalStore
import io.realm.Realm

//import javax.inject.Inject

/**
 * Created by Benoit on 21/06/2017.
 */
class DummyLocalStore: LocalStore() {

    init {

        taskModule = DummyLocalTaskModule()

    }

}