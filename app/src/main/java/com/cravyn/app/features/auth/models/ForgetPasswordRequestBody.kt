package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class ForgetPasswordRequestBody(
    @SerializedName("email")
    val emailAddress: String,
)