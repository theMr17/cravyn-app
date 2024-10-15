package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    @SerializedName("name")
    val name: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("email")
    val emailAddress: String,

    @SerializedName("dateOfBirth")
    val dateOfBirth: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("confirmPassword")
    val confirmPassword: String
)
