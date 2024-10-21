package com.cravyn.app.features.auth

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.ForgetPasswordRequestBody
import com.cravyn.app.features.auth.models.ForgetPasswordResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.OtpVerificationRequestBody
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.ResetPasswordRequestBody
import com.cravyn.app.features.auth.models.ResetPasswordResponse
import com.cravyn.app.features.auth.models.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {
    suspend fun login(body: LoginRequestBody): Response<ApiResponse<LoginResponse>>
    suspend fun logout(): Response<ApiResponse<Unit>>
    suspend fun register(body: RegisterRequestBody): Response<ApiResponse<RegisterResponse>>
    suspend fun forgetPassword(body: ForgetPasswordRequestBody): Response<ApiResponse<ForgetPasswordResponse>>
    suspend fun otpVerification(body: OtpVerificationRequestBody): Response<ApiResponse<Unit>>
    suspend fun resetPassword(body: ResetPasswordRequestBody): Response<ApiResponse<ResetPasswordResponse>>
    fun saveUserToDatabase(user: User): Flow<Resource<Unit>>
}