package com.cravyn.app.features.auth.models

data class RefreshAccessTokenResponse(
    val accessToken: String,
    val reason: String,
    val refreshToken: String
)