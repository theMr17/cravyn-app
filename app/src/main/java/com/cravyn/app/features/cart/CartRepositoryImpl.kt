package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.model.CartResponse
import com.cravyn.app.features.cart.model.IncrementItemCountRequestBody
import retrofit2.Response
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApi: CartApi
) : CartRepository {
    override suspend fun addItemtoCart(body: AddItemtoCartRequestBody): Response<ApiResponse<AddItemToCartResponse>> {
        return cartApi.addItemtoCart(body)
    }

    override suspend fun getCart(): Response<ApiResponse<CartResponse>> {
        return cartApi.getCart()
    }

    override suspend fun incrementItemCount(body: IncrementItemCountRequestBody): Response<ApiResponse<CartResponse>> {
        return cartApi.incrementItemCount(body)
    }

    override suspend fun decrementItemCount(body: DecrementItemCountRequestBody): Response<ApiResponse<CartResponse>> {
        return cartApi.decrementItemCount(body)
    }

    override suspend fun deleteItemFromCart(itemId: String): Response<ApiResponse<CartResponse>> {
        return cartApi.deleteItemFromCart(itemId)
    }
}
