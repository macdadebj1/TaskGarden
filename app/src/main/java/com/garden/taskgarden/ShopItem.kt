package com.garden.taskgarden

/**
 * Class to represent an item in the shop.
 * If you don't supply a resource name to the constructor, will just use the name of the item
 * as a resource name.
 *
 * @author Blake MacDade
 */
class ShopItem {
    var name: String
        private set
    var description: String
        private set
    var price: Int
        private set
    var resourceName: String
        private set

    constructor(name: String, description: String, price: Int, resourceName: String) {
        this.name = name
        this.description = description
        this.price = price
        this.resourceName = resourceName
    }

    constructor(name: String, description: String, price: Int) {
        this.name = name
        this.description = description
        this.price = price
        resourceName = name
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun updateDescription(description: String) {
        this.description = description
    }

    fun updatePrice(price: Int) {
        this.price = price
    }

    fun updateResourceName(resourceName: String) {
        this.resourceName = resourceName
    }
}