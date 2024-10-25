package com.cravyn.app.features.auth

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.auth.models.DeleteAccountRequestBody
import com.cravyn.app.features.auth.models.ForgotPasswordRequestBody
import com.cravyn.app.features.auth.models.ForgotPasswordResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.OtpVerificationRequestBody
import com.cravyn.app.features.auth.models.RefreshAccessTokenRequestBody
import com.cravyn.app.features.auth.models.RefreshAccessTokenResponse
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.ResetPasswordRequestBody
import com.cravyn.app.features.auth.models.ResetPasswordResponse
import com.cravyn.app.features.auth.models.UpdateAccountRequestBody
import com.cravyn.app.features.auth.models.UpdateAccountResponse
import com.cravyn.app.features.auth.models.UpdateProfileImageRequestBody
import com.cravyn.app.features.auth.models.UpdateProfileImageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("customer/register")
    suspend fun register(
        @Body body: RegisterRequestBody
    ): Response<ApiResponse<RegisterResponse>>

    @POST("customer/login")
    suspend fun login(
        @Body body: LoginRequestBody
    ): Response<ApiResponse<LoginResponse>>

    @POST("customer/logout")
    suspend fun logout(): Response<ApiResponse<Unit>>

    @PATCH("customer")
    suspend fun updateAccount(
        @Body body: UpdateAccountRequestBody
    ): Response<ApiResponse<UpdateAccountResponse>>

    @DELETE("customer")
    suspend fun deleteAccount(
        @Body body: DeleteAccountRequestBody
    ): Response<ApiResponse<Unit>>

    @PATCH("customer/profile-image")
    suspend fun updateProfileImage(
        @Body body: UpdateProfileImageRequestBody
    ): Response<ApiResponse<UpdateProfileImageResponse>>

    @POST("customer/refresh-token")
    fun refreshAccessToken(
        @Body body: RefreshAccessTokenRequestBody
    ): Call<ApiResponse<RefreshAccessTokenResponse>>

    @POST("forgot-password")
    suspend fun forgotPassword(
        @Query("userType") userType: String = "customer",
        @Body body: ForgotPasswordRequestBody
    ): Response<ApiResponse<ForgotPasswordResponse>>

    @POST("forgot-password/verify-otp")
    suspend fun otpVerification(
        @Query("userType") userType: String = "customer",
        @Body body: OtpVerificationRequestBody
    ): Response<ApiResponse<Unit>>

    @PATCH("forgot-password/reset-password")
    suspend fun resetPassword(
        @Query("userType") userType: String = "customer",
        @Body body: ResetPasswordRequestBody
    ): Response<ApiResponse<ResetPasswordResponse>>
}
