package com.cravyn.app.features.auth

import com.cravyn.app.data.api.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing JWT tokens in the application.
 *
 * This repository provides methods for accessing and updating JWT tokens
 * in the local database.
 */
interface JwtTokenRepository {
    /**
     * Retrieves the access token from the database.
     *
     * @return A flow emitting the access token as a [Resource].
     */
    fun getAccessTokenFromDatabase(): Flow<Resource<String>>

    /**
     * Retrieves the refresh token from the database.
     *
     * @return A flow emitting the refresh token as a [Resource].
     */
    fun getRefreshTokenFromDatabase(): Flow<Resource<String>>

    /**
     * Updates the access and refresh tokens in the database.
     *
     * @param accessToken The new access token to be stored.
     * @param refreshToken The new refresh token to be stored.
     * @return A flow emitting the result of the update operation as a [Resource].
     */
    fun updateAccessAndRefreshTokenOnDatabase(
        accessToken: String,
        refreshToken: String
    ): Flow<Resource<Unit>>
}
