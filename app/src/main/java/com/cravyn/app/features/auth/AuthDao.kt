package com.cravyn.app.features.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.cravyn.app.features.auth.models.User

@Dao
interface AuthDao {
    /**
     * Inserts a [User] into the database. Ensures that there is only one user in the database.
     *
     * @param user The user object to insert.
     */
    @Transaction
    suspend fun insertUser(user: User) {
        // Delete the existing user if any.
        deleteUser()
        // Now insert the new user.
        insertSingleUser(user)
    }

    /**
     * Inserts a [User] into the database.
     *
     * @param user The user object to insert.
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertSingleUser(user: User)

    /**
     * Deletes the only [User] from the database.
     */
    @Query("DELETE FROM User")
    suspend fun deleteUser()

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
     * Checks if an user exists in the database.
     *
     * @return True if an user exists, false otherwise.
     */
    @Query("SELECT COUNT(*) FROM User")
    suspend fun getUserCount(): Int
}
