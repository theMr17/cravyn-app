package com.cravyn.app.features.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.cravyn.app.features.auth.models.User

@Dao
interface AuthDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User)

    @Query("UPDATE User SET access_token = :accessToken, refresh_token = :refreshToken WHERE id = :userId")
    suspend fun updateAccessAndRefreshToken(
        userId: String,
        accessToken: String,
        refreshToken: String
    )

    @Query("UPDATE User SET access_token = NULL, refresh_token = NULL WHERE id = :userId")
    suspend fun clearAccessAndRefreshToken(userId: String)
}
