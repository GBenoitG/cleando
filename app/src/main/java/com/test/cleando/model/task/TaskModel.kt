package com.test.cleando.model.task

/**
 * Created by Benoit on 20/06/2017.
 */
class TaskModel constructor(var title: String = "", var description: String = "", var status: Status) {

    var id: Int = -1

    constructor(id: Int, title: String = "", description: String = "", status: Status) : this(title, description, status) {
        this.id = id
    }

}