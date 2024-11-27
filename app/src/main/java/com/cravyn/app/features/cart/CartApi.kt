package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CartApi {
    @POST("customer/cart/item")
    suspend fun addItemtoCart(
        @Body body: AddItemtoCartRequestBody
    ): Response<ApiResponse<AddItemToCartResponse>>
}