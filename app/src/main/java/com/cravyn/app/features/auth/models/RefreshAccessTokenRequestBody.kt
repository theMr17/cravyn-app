package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class RefreshAccessTokenRequestBody(
    @SerializedName("refreshToken")
    val refreshToken: String
)
