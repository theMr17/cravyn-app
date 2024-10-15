package com.cravyn.app.features.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.cravyn.app.features.auth.models.User

@Dao
interface AuthDao {
    /**
     * Inserts a [User] into the database. Replaces the existing entry if there is a conflict.
     *
     * @param user The user object to insert.
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Retrieves the access_token for the user.
     *
     * @return The access token as a [String].
     */
    @Query("SELECT access_token FROM User LIMIT 1")
    suspend fun getAccessToken(): String?

    /**
     * Retrieves the refresh_token for the user.
     *
     * @return The refresh token as a [String].
     */
    @Query("SELECT refresh_token FROM User LIMIT 1")
    suspend fun getRefreshToken(): String?

    /**
     * Updates the access and refresh tokens for the first user in the database.
     *
     * @param accessToken The new access token to set.
     * @param refreshToken The new refresh token to set.
     */
    @Query("UPDATE User SET access_token = :accessToken, refresh_token = :refreshToken WHERE id = (SELECT id FROM User LIMIT 1)")
    suspend fun updateAccessAndRefreshToken(
        accessToken: String,
        refreshToken: String
    )

    /**
     * Clears the access and refresh tokens for the first user in the database.
     */
    @Query("UPDATE User SET access_token = NULL, refresh_token = NULL WHERE id = (SELECT id FROM User LIMIT 1)")
    suspend fun clearAccessAndRefreshToken()
}
