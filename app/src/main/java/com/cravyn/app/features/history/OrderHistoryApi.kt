package com.cravyn.app.features.history

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.history.models.OrderHistoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderHistoryApi {
    @GET("/customer/order-history")
    fun getOrderHistory(): Response<ApiResponse<OrderHistoryResponse>>

    @POST("/customer/cancel-order")
    fun cancelOrder(
        @Query("orderId") orderId: String
    ): Response<ApiResponse<Unit>>
}
