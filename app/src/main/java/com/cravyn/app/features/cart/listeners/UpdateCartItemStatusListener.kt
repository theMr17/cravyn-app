package com.cravyn.app.features.cart.listeners

interface UpdateCartItemStatusListener {

    fun onPlusButtonClicked(itemId: String)

    fun onMinusButtonClicked(itemId: String)

    fun onRemoveItemButtonClicked(itemId: String)
}
