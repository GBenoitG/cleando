package com.test.cleando.worker.store.local.realm

import android.content.Context
import android.os.Build
import com.test.cleando.BuildConfig
import com.test.cleando.tool.Injector
import com.test.cleando.worker.store.local.LocalStore
import io.realm.Realm
import io.realm.RealmConfiguration

//import javax.inject.Inject

/**
 * Created by Benoit on 21/06/2017.
 */
class RealmLocalStore(var context: Context = Injector.context): LocalStore() {

    init {

        Realm.init(context)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().schemaVersion(BuildConfig.RealmDBVersion).build())

        taskModule = RealmLocalTaskModule()

    }

}