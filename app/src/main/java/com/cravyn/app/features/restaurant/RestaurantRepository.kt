package com.cravyn.app.features.restaurant

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.restaurant.models.RestaurantMenuResponse
import retrofit2.Response

interface RestaurantRepository {
    suspend fun getRestaurantMenu(
        restaurantId: String,
        limit: Int
    ): Response<ApiResponse<RestaurantMenuResponse>>
}
