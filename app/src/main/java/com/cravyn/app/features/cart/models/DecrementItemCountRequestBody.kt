package com.cravyn.app.features.cart.models

import com.google.gson.annotations.SerializedName

data class DecrementItemCountRequestBody(
    @SerializedName("itemId")
    val itemId: String
)
