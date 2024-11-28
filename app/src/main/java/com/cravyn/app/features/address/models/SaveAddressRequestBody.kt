package com.cravyn.app.features.address.models

import com.google.gson.annotations.SerializedName

data class SaveAddressRequestBody(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("displayAddress")
    val displayAddress: String
)
