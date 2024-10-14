package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class UpdateAccountRequestBody(
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String
)