package com.cravyn.app.features.history

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.history.models.OrderHistoryResponse
import retrofit2.Response
import javax.inject.Inject

class OrderHistoryRepositoryImpl @Inject constructor(
    private val orderHistoryApi: OrderHistoryApi
) : OrderHistoryRepository {
    override suspend fun getOrderHistory(): Response<ApiResponse<OrderHistoryResponse>> {
        return orderHistoryApi.getOrderHistory()
    }

    override suspend fun cancelOrder(orderId: String): Response<ApiResponse<Unit>> {
        return orderHistoryApi.cancelOrder(orderId)
    }
}
