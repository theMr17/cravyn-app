package com.cravyn.app.features.auth.models

data class RegisterResponse(
    val date_of_birth: String,
    val email_address: String,
    val id: String,
    val name: String,
    val phone_number: String
)