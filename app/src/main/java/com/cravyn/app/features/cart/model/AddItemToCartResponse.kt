package com.cravyn.app.features.cart.model


import com.google.gson.annotations.SerializedName

data class AddItemToCartResponse(
    @SerializedName("item_id")
    val itemId: String,
    @SerializedName("quantity")
    val quantity: Int
)