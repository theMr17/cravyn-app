package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class OtpVerificationRequestBody(
    @SerializedName("otp")
    val otp: String,

    @SerializedName("email")
    val emailAddress: String
)
