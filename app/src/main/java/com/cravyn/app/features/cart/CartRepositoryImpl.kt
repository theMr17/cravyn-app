package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.safeCall
import com.cravyn.app.features.cart.models.AddItemToCartResponse
import com.cravyn.app.features.cart.models.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.models.CartResponse
import com.cravyn.app.features.cart.models.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.models.IncrementItemCountRequestBody
import com.cravyn.app.features.cart.models.PlaceOrderRequestBody
import retrofit2.Response
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApi: CartApi
) : CartRepository {
    override suspend fun addItemToCart(body: AddItemtoCartRequestBody): Response<ApiResponse<AddItemToCartResponse>> {
        return safeCall {
            cartApi.addItemToCart(body)
        }
    }

    override suspend fun getCart(): Response<ApiResponse<CartResponse>> {
        return safeCall {
            cartApi.getCart()
        }
    }

    override suspend fun incrementItemCount(body: IncrementItemCountRequestBody): Response<ApiResponse<CartResponse>> {
        return safeCall {
            cartApi.incrementItemCount(body)
        }
    }

    override suspend fun decrementItemCount(body: DecrementItemCountRequestBody): Response<ApiResponse<CartResponse>> {
        return safeCall {
            cartApi.decrementItemCount(body)
        }
    }

    override suspend fun deleteItemFromCart(itemId: String): Response<ApiResponse<CartResponse>> {
        return safeCall {
            cartApi.deleteItemFromCart(itemId)
        }
    }

    override suspend fun placeOrder(body: PlaceOrderRequestBody): Response<ApiResponse<Unit>> {
        return safeCall {
            cartApi.placeOrder(body)
        }
    }
}
