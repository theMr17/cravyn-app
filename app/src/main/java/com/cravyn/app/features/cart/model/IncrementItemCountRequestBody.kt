package com.cravyn.app.features.cart.model

import com.google.gson.annotations.SerializedName

data class IncrementItemCountRequestBody (
    @SerializedName("itemId")
    val itemId : String
)