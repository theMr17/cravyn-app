package com.cravyn.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cravyn.app.features.auth.AuthDao
import com.cravyn.app.features.auth.models.User

/**
 * Represents the Room database for the Cravyn application.
 * This class serves as the main access point for the application's database
 * and contains the database configuration, including the database version
 * and the entities that it will hold.
 */
@Database(
    entities = [User::class],
    version = 1
)
abstract class CravynDatabase : RoomDatabase() {
    /**
     * Gets an instance of [AuthDao] for accessing authentication-related data operations.
     *
     * @return An instance of [AuthDao] to interact with the User entity in the database.
     */
    abstract fun authDao(): AuthDao
}
