package com.bendev.cleando.tool

import android.content.Context
import com.bendev.cleando.App
import com.bendev.cleando.tool.dagger.module.ContextDaggerModule
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