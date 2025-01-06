package com.cravyn.app.features.profile

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.safeCall
import com.cravyn.app.features.profile.models.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): Response<ApiResponse<ProfileResponse>> {
        return safeCall {
            profileApi.getProfile()
        }
    }
}
