package com.cravyn.app.features.address.models

import com.google.gson.annotations.SerializedName

data class RemoveAddressRequestBody(
    @SerializedName("addressId")
    val addressId: String
)
