package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequestBody(
    @SerializedName("image")
    val image: String
)