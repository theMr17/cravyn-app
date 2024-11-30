package com.cravyn.app.features.cart.models

import com.google.gson.annotations.SerializedName

data class AddItemtoCartRequestBody(
    @SerializedName("itemId")
    val itemId: String
)
