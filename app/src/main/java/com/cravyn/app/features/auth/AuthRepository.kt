package com.cravyn.app.features.auth

import com.cravyn.app.data.ApiResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun login(body: LoginRequestBody): Response<ApiResponse<LoginResponse>>
    suspend fun logout(): Response<ApiResponse<Void>>
}
