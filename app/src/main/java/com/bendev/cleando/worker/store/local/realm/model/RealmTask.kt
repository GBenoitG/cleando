package com.bendev.cleando.worker.store.local.realm.model

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

/**
 * Created by Benoit on 21/06/2017.
 */
open class RealmTask : RealmObject() {

    companion object {

        val ID = "id"

        val IS_DONE = "isDone"

    }

    @PrimaryKey
    var id: Int = 0

    var title: String = ""
    var description: String = ""
    @Index
    var isDone: Boolean = false
}