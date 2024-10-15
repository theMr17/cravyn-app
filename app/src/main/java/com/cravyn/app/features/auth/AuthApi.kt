package com.cravyn.app.features.auth

import com.cravyn.app.data.ApiResponse
import com.cravyn.app.features.auth.models.DeleteAccountRequestBody
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.RefreshAccessTokenResponse
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.UpdateAccountRequestBody
import com.cravyn.app.features.auth.models.UpdateAccountResponse
import com.cravyn.app.features.auth.models.UpdateProfileImageRequestBody
import com.cravyn.app.features.auth.models.UpdateProfileImageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("/customer/register")
    suspend fun register(
        @Body body: RegisterRequestBody
    ): Response<ApiResponse<RegisterResponse>>

    @POST("/customer/login")
    suspend fun login(
        @Body body: LoginRequestBody
    ): Response<ApiResponse<LoginResponse>>

    @POST("/customer/logout")
    suspend fun logout(): Response<ApiResponse<Void>>

    @PATCH("/customer")
    suspend fun updateAccount(
        @Body body: UpdateAccountRequestBody
    ): Response<ApiResponse<UpdateAccountResponse>>

    @DELETE("/customer")
    suspend fun deleteAccount(
        @Body body: DeleteAccountRequestBody
    ): Response<ApiResponse<Void>>

    @PATCH("/customer/profile-image")
    suspend fun updateProfileImage(
        @Body body: UpdateProfileImageRequestBody
    ): Response<ApiResponse<UpdateProfileImageResponse>>

    @PATCH("/customer/refresh-token")
    suspend fun refreshAccessToken(): Response<ApiResponse<RefreshAccessTokenResponse>>
}
