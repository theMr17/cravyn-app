package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequestBody(
    @SerializedName("email")
    val emailAddress: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("confirmPassword")
    val confirmPassword: String,

    @SerializedName("otp")
    val otp: String
)
