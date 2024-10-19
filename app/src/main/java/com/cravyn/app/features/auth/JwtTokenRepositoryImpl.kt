package com.cravyn.app.features.auth

import com.cravyn.app.data.api.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JwtTokenRepositoryImpl @Inject constructor(
    private val authDao: AuthDao
) : JwtTokenRepository {
    override fun getAccessTokenFromDatabase(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val accessToken = authDao.getAccessToken()
            emit(Resource.Success(accessToken ?: ""))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error())
        }
    }

    override fun getRefreshTokenFromDatabase(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val refreshToken = authDao.getRefreshToken()
            emit(Resource.Success(refreshToken ?: ""))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error())
        }
    }

    override fun updateAccessAndRefreshTokenOnDatabase(
        accessToken: String,
        refreshToken: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            authDao.updateAccessAndRefreshToken(accessToken, refreshToken)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error())
        }
    }
}
