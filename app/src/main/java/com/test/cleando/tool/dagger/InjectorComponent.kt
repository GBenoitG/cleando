//package com.test.cleando.tool
//
//import android.content.Context
//import com.test.cleando.tool.dagger.ContextDaggerModule
//import com.test.cleando.tool.dagger.LocalStoreDaggerModule
//import com.test.cleando.worker.TaskWorker
//import com.test.cleando.worker.store.local.LocalStore
//import com.test.cleando.worker.store.local.realm.RealmLocalStore
//import dagger.Component
//import javax.inject.Singleton
//
///**
// * Created by Benoit on 21/06/2017.
// */
//@Singleton
//@Component (modules = arrayOf(ContextDaggerModule::class, LocalStoreDaggerModule::class))
//interface InjectorComponent {
//
////    fun context(): Context
//    fun inject(realmLocalStore: RealmLocalStore)
//
////    fun localStore(): LocalStore
//    fun inject(taskWorker: TaskWorker)
//
//}