package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class RefreshAccessTokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("accessToken")
    val refreshToken: String
)
