package com.bendev.cleando.tool

import android.content.Context
import com.bendev.cleando.worker.store.local.LocalStore
import com.bendev.cleando.worker.store.local.realm.DummyLocalStore
import com.bendev.cleando.worker.store.local.realm.RealmLocalStore

/**
 * Created by Benoit on 26/06/2017.
 */
object Injector {

    lateinit var context: Context

    lateinit var localStore: LocalStore

    fun configure(c: Context) {

        context = c

        when (Constants.ENV) {

            Constants.Environment.DEV -> localStore = RealmLocalStore()

            Constants.Environment.FIXTURES -> localStore = DummyLocalStore()

        }
    }

}