package com.cravyn.app.features.auth.models

import com.google.gson.annotations.SerializedName

data class DeleteRequestBody(
    @SerializedName("password")
    val password: String
)