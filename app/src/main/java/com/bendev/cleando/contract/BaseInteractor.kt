package com.bendev.cleando.contract

/**
 * Created by Benoit on 20/06/2017.
 */
abstract class BaseInteractor<P : BasePresenter<*>> {

    lateinit var output : P

}