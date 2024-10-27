package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class UpdateProfileImageRequestBody(
    @SerializedName("image")
    val image: String
)
