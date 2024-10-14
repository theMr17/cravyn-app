package com.cravyn.app.features.auth

import com.cravyn.app.data.ApiResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userDao: AuthDao
) : AuthRepository {
    override suspend fun login(body: LoginRequestBody): Response<ApiResponse<LoginResponse>> {
        return authApi.login(body)
    }

    override suspend fun logout(): Response<ApiResponse<Void>> {
        return authApi.logout()
    }
}
