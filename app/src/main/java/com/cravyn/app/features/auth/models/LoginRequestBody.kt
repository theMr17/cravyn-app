package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("email")
    val emailAddress: String,

    @SerializedName("password")
    val password: String
)
