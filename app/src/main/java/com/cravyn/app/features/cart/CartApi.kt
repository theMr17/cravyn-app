package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import com.cravyn.app.features.cart.model.GetCartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartApi {
    @POST("customer/cart/item")
    suspend fun addItemtoCart(
        @Body body: AddItemtoCartRequestBody
    ): Response<ApiResponse<AddItemToCartResponse>>

    @GET("customer/cart/")
    suspend fun getCart(): Response<ApiResponse<GetCartResponse>>

}