package com.test.cleando.tool.router

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity

/**
 * Created by Benoit on 28/10/2017.
 */
object Navigator {

    interface Router {

        fun getRouterActivity(): AppCompatActivity

        fun startActivity(intent: Intent, requestCode: Int? = null, options: ActivityOptionsCompat? = null)

    }

}