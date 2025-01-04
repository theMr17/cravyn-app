package com.cravyn.app.features.history

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.history.models.OrderHistoryResponse
import retrofit2.Response

interface OrderHistoryRepository {
    suspend fun getOrderHistory(): Response<ApiResponse<OrderHistoryResponse>>
    suspend fun cancelOrder(orderId: String): Response<ApiResponse<Unit>>
}
