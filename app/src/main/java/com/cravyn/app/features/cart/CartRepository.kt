package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import com.cravyn.app.features.cart.model.GetCartResponse
import retrofit2.Response

interface CartRepository {

    suspend fun addItemtoCart(body: AddItemtoCartRequestBody): Response<ApiResponse<AddItemToCartResponse>>

    suspend fun getCart(): Response<ApiResponse<GetCartResponse>>
}