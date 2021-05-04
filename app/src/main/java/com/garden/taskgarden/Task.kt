package com.garden.taskgarden

/**
 * Task represents a task object
 *
 * @author Blake MacDade
 */
class Task {
    /**
     * getID
     *
     * @return the id of the object.
     */
    var iD = 0
        private set

    /**
     * getTitle getter method for the Task's title.
     *
     * @return title string of the object.
     */
    var title: String? = null
        private set

    /**
     * getDescription
     *
     * @return description of the object.
     */
    var description: String? = null
        private set

    /**
     * getCompleted
     *
     * @return the completed state of the object.
     */
    var completed = false
        private set

    /**
     * getTimeToCompleteBy
     *
     * @return time to complete the task by.
     */
    var timeToCompletedBy = 0
        private set

    /**
     * Constructor for task object.
     *
     * @param title title of the task.
     * @param description description of the task.
     * @param completeBy date to complete the task by.
     */
    constructor(title: String?, description: String?, completeBy: Int) {
        this.title = title
        this.description = description
        timeToCompletedBy = completeBy
        completed = false
    }

    /**
     * Blank constructor for Task.
     * Instantiates all values to their defaults.
     */
    constructor() {} //Should probably remove..? doesn't really matter, just for simple testing...

    /**
     * updateID updates the id of the object.
     *
     * @param ID the new ID to give the object.
     */
    fun updateID(ID: Int) {
        iD = ID
    }

    /**
     * updateTitle updates the title of the object.
     *
     * @param newTitle the new title to give the object.
     */
    fun updateTitle(newTitle: String?) {
        title = newTitle
    }

    /**
     * updateDescription updates the description of the object.
     *
     * @param newDescription the new description to give the object.
     */
    fun updateDescription(newDescription: String?) {
        description = newDescription
    }

    /**
     * updateCompleted updates the completed state of the object.
     *
     * @param state the new completed state to give the object.
     */
    fun updateCompleted(state: Boolean) {
        completed = state
    }

    /**
     * updateCompletedBy updates the time to complete by of the object.
     *
     * @param time the new time to complete by to give the object.
     */
    fun updateCompletedBy(time: Int) {
        timeToCompletedBy = time
    }

    /**
     * toString
     *
     * @return a string representation of the task title and the task description.
     */
    override fun toString(): String {
        return "$title;$description;"
    }
}