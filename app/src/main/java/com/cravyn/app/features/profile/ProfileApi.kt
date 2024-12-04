package com.cravyn.app.features.profile

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.profile.models.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {
    @GET("customer")
    suspend fun getProfile(): Response<ApiResponse<ProfileResponse>>
}
