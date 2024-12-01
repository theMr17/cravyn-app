package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.models.AddItemToCartResponse
import com.cravyn.app.features.cart.models.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.models.CartResponse
import com.cravyn.app.features.cart.models.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.models.IncrementItemCountRequestBody
import com.cravyn.app.features.cart.models.PlaceOrderRequestBody
import retrofit2.Response

interface CartRepository {

    suspend fun addItemToCart(body: AddItemtoCartRequestBody): Response<ApiResponse<AddItemToCartResponse>>

    suspend fun getCart(): Response<ApiResponse<CartResponse>>

    suspend fun incrementItemCount(body: IncrementItemCountRequestBody): Response<ApiResponse<CartResponse>>

    suspend fun decrementItemCount(body: DecrementItemCountRequestBody): Response<ApiResponse<CartResponse>>

    suspend fun deleteItemFromCart(itemId: String): Response<ApiResponse<CartResponse>>

    suspend fun placeOrder(body: PlaceOrderRequestBody): Response<ApiResponse<Unit>>
}
