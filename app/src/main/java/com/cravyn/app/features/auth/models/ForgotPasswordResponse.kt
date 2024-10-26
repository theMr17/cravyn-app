package com.cravyn.app.features.auth.models


import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("mailResponse")
    val mailResponse: MailResponse,
    @SerializedName("user")
    val user: User
) {
    data class MailResponse(
        @SerializedName("accepted")
        val accepted: List<String>,
        @SerializedName("ehlo")
        val ehlo: List<String>,
        @SerializedName("envelope")
        val envelope: Envelope,
        @SerializedName("envelopeTime")
        val envelopeTime: Int,
        @SerializedName("messageId")
        val messageId: String,
        @SerializedName("messageSize")
        val messageSize: Int,
        @SerializedName("messageTime")
        val messageTime: Int,
        @SerializedName("rejected")
        val rejected: List<Any>,
        @SerializedName("response")
        val response: String
    ) {
        data class Envelope(
            @SerializedName("from")
            val from: String,
            @SerializedName("to")
            val to: List<String>
        )
    }

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
