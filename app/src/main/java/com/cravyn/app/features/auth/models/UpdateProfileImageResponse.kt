package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class UpdateProfileImageResponse(
    @SerializedName("customer")
    val customer: Customer,

    @SerializedName("imageUrl")
    val imageUrl: String
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
        val phoneNumber: String
    )
}