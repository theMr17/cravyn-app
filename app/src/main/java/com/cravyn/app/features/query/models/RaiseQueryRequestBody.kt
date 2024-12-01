package com.cravyn.app.features.query.models

import com.google.gson.annotations.SerializedName

data class RaiseQueryRequestBody(
    @SerializedName("question")
    val question: String
)
