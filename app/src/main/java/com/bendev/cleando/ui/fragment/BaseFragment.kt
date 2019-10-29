package com.bendev.cleando.ui.fragment

import com.bendev.cleando.ui.activity.BaseActivity
import androidx.fragment.app.Fragment
/**
 * Created by Benoit on 20/06/2017.
 */
abstract class BaseFragment : Fragment() {

    val activity: BaseActivity
        get() = getActivity() as BaseActivity


}