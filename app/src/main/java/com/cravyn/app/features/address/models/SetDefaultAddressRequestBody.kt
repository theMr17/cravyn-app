package com.cravyn.app.features.address.models

import com.google.gson.annotations.SerializedName

data class SetDefaultAddressRequestBody(
    @SerializedName("addressId")
    val addressId: String
)
