package com.cravyn.app.data.api

data class ApiResponse<T>(
    val statusCode: Int,
    val data: T,
    val message: String,
    val success: Boolean
)
