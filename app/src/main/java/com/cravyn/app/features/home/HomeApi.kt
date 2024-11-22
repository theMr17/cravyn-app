package com.cravyn.app.features.home

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.home.models.RecommendedRestaurantsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("restaurants/recommended")
    suspend fun getRecommendedRestaurants(
        @Query("lat") lat: Double,
        @Query("long") long: Double,
        @Query("minRating") minRating: Float,
        @Query("sortBy") sortBy: String,
        @Query("radius") radius: Float,
        @Query("descending") descending: Boolean,
        @Query("limit") limit: Int,
    ): Response<ApiResponse<RecommendedRestaurantsResponse>>
}
