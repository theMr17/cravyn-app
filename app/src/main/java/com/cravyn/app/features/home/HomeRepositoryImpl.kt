package com.cravyn.app.features.home

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.home.models.RecommendedRestaurantsResponse
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRepository {
    override suspend fun getRecommendedRestaurants(
        lat: Double,
        long: Double,
        minRating: Float,
        sortBy: String,
        radius: Float,
        descending: Boolean,
        limit: Int
    ): Response<ApiResponse<RecommendedRestaurantsResponse>> {
        return homeApi.getRecommendedRestaurants(lat, long, minRating, sortBy, radius, descending, limit)
    }
}
