package com.cravyn.app.features.history

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.safeCall
import com.cravyn.app.features.history.models.OrderHistoryResponse
import retrofit2.Response
import javax.inject.Inject

class OrderHistoryRepositoryImpl @Inject constructor(
    private val orderHistoryApi: OrderHistoryApi
) : OrderHistoryRepository {
    override suspend fun getOrderHistory(): Response<ApiResponse<OrderHistoryResponse>> {
        return safeCall {
            orderHistoryApi.getOrderHistory()
        }
    }

    override suspend fun cancelOrder(orderId: String): Response<ApiResponse<Unit>> {
        return safeCall {
            orderHistoryApi.cancelOrder(orderId)
        }
    }
}
