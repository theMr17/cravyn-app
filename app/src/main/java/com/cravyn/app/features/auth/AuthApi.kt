package com.cravyn.app.features.auth

import com.cravyn.app.data.ApiResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/customer/login")
    suspend fun login(
        @Body body: LoginRequestBody
    ): Response<ApiResponse<LoginResponse>>

    @POST("/customer/logout")
    suspend fun logout(): Response<ApiResponse<Void>>
}
