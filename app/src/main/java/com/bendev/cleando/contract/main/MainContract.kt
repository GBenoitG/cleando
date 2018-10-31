package com.bendev.cleando.contract.main

class MainContract {

    interface Navigation {

        fun goToDetail(taskId: Int)
        fun goToList()

    }

}
