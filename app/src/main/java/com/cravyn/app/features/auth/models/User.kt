package com.cravyn.app.features.auth.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("date_of_birth")
    val dateOfBirth: String,

    @ColumnInfo("email_address")
    val emailAddress: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("phone_number")
    val phoneNumber: String,

    @ColumnInfo("profile_image_url")
    val profileImageUrl: String?,

    @ColumnInfo("access_token")
    val accessToken: String?,

    @ColumnInfo("refresh_token")
    val refreshToken: String?,
)
