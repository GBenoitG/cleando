package com.test.cleando.tool

import android.content.Context
import android.content.res.Resources
import com.test.cleando.App
import com.test.cleando.worker.store.local.LocalStore
import com.test.cleando.worker.store.local.realm.RealmLocalStore

/**
 * Created by Benoit on 26/06/2017.
 */
object Injector {

    lateinit var context: Context

    lateinit var resources: Resources

    lateinit var localStore: LocalStore

    fun configure(app: App) {

        context = app

        resources = context.resources

        localStore = RealmLocalStore()
    }

}