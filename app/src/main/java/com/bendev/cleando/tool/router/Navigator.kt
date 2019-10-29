package com.bendev.cleando.tool.router

import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Benoit on 28/10/2017.
 */
object Navigator {

    interface Router {

        fun getRouterActivity(): AppCompatActivity

        fun startActivity(intent: Intent, requestCode: Int? = null, options: ActivityOptionsCompat? = null)

    }

}