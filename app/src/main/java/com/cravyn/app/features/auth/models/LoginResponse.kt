package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("user")
    val user: Customer
) {
    data class Customer(
        @SerializedName("date_of_birth")
        val dateOfBirth: String,
        @SerializedName("email_address")
        val emailAddress: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("phone_number")
        val phoneNumber: String,
        @SerializedName("profile_image_url")
        val profileImageUrl: String?
    )
}
