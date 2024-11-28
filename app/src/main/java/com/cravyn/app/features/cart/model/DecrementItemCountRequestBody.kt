package com.cravyn.app.features.cart.model

import com.google.gson.annotations.SerializedName

data class DecrementItemCountRequestBody (
    @SerializedName("itemId")
    val itemId : String
)