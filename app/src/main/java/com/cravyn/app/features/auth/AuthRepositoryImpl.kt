package com.cravyn.app.features.auth

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.ForgotPasswordRequestBody
import com.cravyn.app.features.auth.models.ForgotPasswordResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.OtpVerificationRequestBody
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.ResetPasswordRequestBody
import com.cravyn.app.features.auth.models.ResetPasswordResponse
import com.cravyn.app.features.auth.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDao: AuthDao
) : AuthRepository {
    companion object {
        private const val USER_TYPE = "customer"
    }

    override suspend fun login(body: LoginRequestBody): Response<ApiResponse<LoginResponse>> {
        return authApi.login(body)
    }

    override suspend fun logout(): Response<ApiResponse<Unit>> {
        return authApi.logout()
    }

    override suspend fun register(body: RegisterRequestBody): Response<ApiResponse<RegisterResponse>> {
        return authApi.register(body)
    }

    override suspend fun forgotPassword(body: ForgotPasswordRequestBody): Response<ApiResponse<ForgotPasswordResponse>> {
        return authApi.forgotPassword(USER_TYPE, body)
    }

    override suspend fun otpVerification(body: OtpVerificationRequestBody): Response<ApiResponse<Unit>> {
        return authApi.otpVerification(USER_TYPE, body)
    }

    override suspend fun resetPassword(body: ResetPasswordRequestBody): Response<ApiResponse<ResetPasswordResponse>> {
        return authApi.resetPassword(USER_TYPE, body)
    }

    override fun saveUserToDatabase(user: User): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            authDao.insertUser(user)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Failed to save user to database: ${e.message}"))
        }
    }

    override fun deleteUserFromDatabase(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            authDao.deleteUser()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Failed to delete user from database: ${e.message}"))
        }
    }

    override fun isUserLoggedIn(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val isUserLoggedIn = authDao.getUserCount() > 0
            emit(Resource.Success(isUserLoggedIn))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Failed to check user login status: ${e.message}"))
        }
    }
}
