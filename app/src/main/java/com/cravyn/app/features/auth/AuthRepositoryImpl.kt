package com.cravyn.app.features.auth

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDao: AuthDao
) : AuthRepository {
    override suspend fun login(body: LoginRequestBody): Response<ApiResponse<LoginResponse>> {
        return authApi.login(body)
    }

    override suspend fun logout(): Response<ApiResponse<Unit>> {
        return authApi.logout()
    }

    override suspend fun register(body: RegisterRequestBody): Response<ApiResponse<RegisterResponse>> {
        return authApi.register(body)
    }

    override fun addUserToDatabase(user: User): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            authDao.insertUser(user)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error())
        }
    }
}
