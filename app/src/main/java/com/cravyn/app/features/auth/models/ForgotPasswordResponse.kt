package com.cravyn.app.features.auth.models


import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("user")
    val user: User
) {
    data class User(
        @SerializedName("email_address")
        val emailAddress: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("phone_number")
        val phoneNumber: Any?
    )
}
