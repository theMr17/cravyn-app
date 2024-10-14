package com.cravyn.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cravyn.app.features.auth.AuthDao
import com.cravyn.app.features.auth.models.Customer

@Database(
    entities = [Customer::class],
    exportSchema = false,
    version = 1
)
abstract class CravynDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
}
