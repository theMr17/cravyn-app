package com.cravyn.app.features.auth

import com.cravyn.app.data.api.Resource
import kotlinx.coroutines.flow.Flow

interface JwtTokenRepository {
    fun getAccessTokenFromDatabase(): Flow<Resource<String>>
    fun getRefreshTokenFromDatabase(): Flow<Resource<String>>
    fun updateAccessAndRefreshTokenOnDatabase(
        accessToken: String,
        refreshToken: String
    ): Flow<Resource<Unit>>
}
