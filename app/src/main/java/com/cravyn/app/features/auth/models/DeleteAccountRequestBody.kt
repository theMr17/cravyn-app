package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class DeleteAccountRequestBody(
    @SerializedName("password")
    val password: String
)
