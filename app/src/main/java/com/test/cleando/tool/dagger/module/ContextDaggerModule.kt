package com.test.cleando.tool.dagger.module

import android.content.Context
import com.test.cleando.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
* Created by Benoit on 22/06/2017.
*/

@Module
class ContextDaggerModule (val app: App) {

    @Provides
    @Singleton
    fun provideContext(): App {
        return app
    }

}
