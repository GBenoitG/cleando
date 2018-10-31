package com.bendev.cleando.ui.fragment

import android.support.v4.app.Fragment
import com.bendev.cleando.ui.activity.BaseActivity

/**
 * Created by Benoit on 20/06/2017.
 */
abstract class BaseFragment : Fragment() {

    val activity: BaseActivity
        get() = getActivity() as BaseActivity


}