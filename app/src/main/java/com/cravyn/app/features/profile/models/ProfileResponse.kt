package com.cravyn.app.features.profile.models


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("customer")
    val customer: Customer
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
