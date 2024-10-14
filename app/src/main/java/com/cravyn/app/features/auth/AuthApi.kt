package com.cravyn.app.features.auth

import com.cravyn.app.data.ApiResponse
import com.cravyn.app.features.auth.models.DeleteRequestBody
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.RefreshAccessTokenResponse
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.UpdateAccountRequestBody
import com.cravyn.app.features.auth.models.UpdateAccountResponse
import com.cravyn.app.features.auth.models.UpdateProfileRequestBody
import com.cravyn.app.features.auth.models.UpdateProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("/customer/login")
    suspend fun login(
        @Body body: LoginRequestBody
    ): Response<ApiResponse<LoginResponse>>

    @POST("/customer/logout")
    suspend fun logout(): Response<ApiResponse<Void>>

    @DELETE("/customer")
    suspend fun delete(
        @Body body: DeleteRequestBody
    ): Response<ApiResponse<Void>>

    @POST("/customer/register")
    suspend fun register(
        @Body body: RegisterRequestBody
    ): Response<ApiResponse<RegisterResponse>>

    @PATCH("/customer")
    suspend fun updateProfile(
        @Body body: UpdateProfileRequestBody
    ): Response<ApiResponse<UpdateProfileResponse>>

    @PATCH("/customer/profile-image")
    suspend fun updateAccount(
        @Body body: UpdateAccountRequestBody
    ): Response<ApiResponse<UpdateAccountResponse>>

    @PATCH("/customer/refresh-token")
    suspend fun refreshAccessToken(): Response<ApiResponse<RefreshAccessTokenResponse>>
}
