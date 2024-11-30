package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.CartResponse
import com.cravyn.app.features.cart.model.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.model.IncrementItemCountRequestBody
import retrofit2.Response

interface CartRepository {

    suspend fun addItemToCart(body: AddItemtoCartRequestBody): Response<ApiResponse<AddItemToCartResponse>>

    suspend fun getCart(): Response<ApiResponse<CartResponse>>

    suspend fun incrementItemCount(body: IncrementItemCountRequestBody): Response<ApiResponse<CartResponse>>

    suspend fun decrementItemCount(body: DecrementItemCountRequestBody): Response<ApiResponse<CartResponse>>

    suspend fun deleteItemFromCart(itemId: String): Response<ApiResponse<CartResponse>>

}
