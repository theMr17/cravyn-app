package com.cravyn.app.features.cart.listener

interface UpdateItemStatusListener {

    fun incrementItemClicked(itemId: String)

    fun decrementItemClicked(itemId: String)

    fun deleteItemClicked(itemId: String)
}