package com.cravyn.app.features.cart.models

import com.google.gson.annotations.SerializedName

data class PlaceOrderRequestBody(
    @SerializedName("specifications")
    val specifications: String,
    @SerializedName("addressId")
    val addressId: String
)
