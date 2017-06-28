package com.test.cleando.contract

/**
 * Created by Benoit on 20/06/2017.
 */
abstract class BasePresenter<T : BaseController> {

    lateinit var output: T

}