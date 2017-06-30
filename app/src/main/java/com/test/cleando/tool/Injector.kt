package com.test.cleando.tool

import android.content.Context
import com.test.cleando.worker.store.local.LocalStore
import com.test.cleando.worker.store.local.realm.DummyLocalStore
import com.test.cleando.worker.store.local.realm.RealmLocalStore

/**
 * Created by Benoit on 26/06/2017.
 */
object Injector {

    lateinit var context: Context

    lateinit var localStore: LocalStore

    fun configure(c: Context) {

        context = c

        localStore = when (Constants.ENV) {

            Constants.Environment.DEV -> RealmLocalStore()

            Constants.Environment.FIXTURES -> DummyLocalStore()

        }
    }

}