package com.cravyn.app.features.home

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.home.models.RecommendedRestaurantsResponse
import retrofit2.Response

interface HomeRepository {
    suspend fun getRecommendedRestaurants(
        lat: Double,
        long: Double,
        minRating: Float,
        sortBy: String,
        radius: Float,
        descending: Boolean,
        limit: Int
    ): Response<ApiResponse<RecommendedRestaurantsResponse>>
}
