package com.cravyn.app.features.restaurant

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.safeCall
import com.cravyn.app.features.restaurant.models.RestaurantMenuResponse
import retrofit2.Response
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApi
) : RestaurantRepository {
    override suspend fun getRestaurantMenu(
        restaurantId: String,
        limit: Int
    ): Response<ApiResponse<RestaurantMenuResponse>> {
        return safeCall {
            restaurantApi.getRestaurantMenu(restaurantId, limit)
        }
    }
}
