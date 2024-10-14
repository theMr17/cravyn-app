package com.cravyn.app.di

import android.content.Context
import androidx.room.Room
import com.cravyn.app.data.room.CravynDatabase
import com.cravyn.app.features.auth.AuthDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun providesCravynDatabase(
        @ApplicationContext context: Context
    ): CravynDatabase {
        return Room.databaseBuilder(context, CravynDatabase::class.java, "cravyn-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getUserDao(database: CravynDatabase): AuthDao {
        return database.authDao()
    }
}
