package com.test.cleando.tool

import android.content.Context
import com.test.cleando.App
import com.test.cleando.tool.dagger.module.ContextDaggerModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Benoit on 21/06/2017.
 */
@Singleton
@Component (modules = arrayOf(ContextDaggerModule::class))
interface InjectorComponent {

    var app: App

}