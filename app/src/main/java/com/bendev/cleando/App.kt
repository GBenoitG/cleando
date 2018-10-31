package com.bendev.cleando

import android.app.Application
import com.bendev.cleando.tool.Injector

//import com.bendev.cleando.tool.DaggerInjectorComponent
//import com.bendev.cleando.tool.InjectorComponent
//import com.bendev.cleando.tool.dagger.ContextDaggerModule

/**
 * Created by Benoit on 21/06/2017.
 */
class App : Application() {

//    companion object {
//        lateinit var injectorComponent: InjectorComponent
//    }

    override fun onCreate() {
        super.onCreate()

//        injectorComponent = DaggerInjectorComponent.builder()
//                .contextDaggerModule(ContextDaggerModule(this))
//                .build()

        Injector.configure(this)

    }

}