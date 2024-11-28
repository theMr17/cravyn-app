package com.cravyn.app.features.cart.model

import com.google.gson.annotations.SerializedName

data class DeleteItemFomCartRequestBody(
    @SerializedName("itemId")
    val itemId : String
)
