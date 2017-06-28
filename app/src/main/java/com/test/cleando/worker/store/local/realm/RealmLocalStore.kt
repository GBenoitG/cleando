package com.test.cleando.worker.store.local.realm

import android.content.Context
import com.test.cleando.tool.Injector
import com.test.cleando.worker.store.local.LocalStore
import io.realm.Realm

//import javax.inject.Inject

/**
 * Created by Benoit on 21/06/2017.
 */
class RealmLocalStore(var context: Context = Injector.context): LocalStore(/*context*/) {

    init {

//        App.injectorComponent.inject(this)

        Realm.init(context)

        taskModule = RealmLocalTaskModule()

    }

}