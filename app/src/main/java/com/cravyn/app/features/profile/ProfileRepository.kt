package com.cravyn.app.features.profile

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.profile.models.ProfileResponse
import retrofit2.Response

interface ProfileRepository {
    suspend fun getProfile(): Response<ApiResponse<ProfileResponse>>
}
