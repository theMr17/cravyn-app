package com.cravyn.app.features.restaurant

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.restaurant.models.RestaurantMenuResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {
    @GET("restaurants/menu")
    suspend fun getRestaurantMenu(
        @Query("restaurantId") restaurantId: String,
        @Query("limit") limit: Int,
    ): Response<ApiResponse<RestaurantMenuResponse>>
}
