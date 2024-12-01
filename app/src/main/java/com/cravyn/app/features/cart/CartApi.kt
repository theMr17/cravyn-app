package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.models.AddItemToCartResponse
import com.cravyn.app.features.cart.models.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.models.CartResponse
import com.cravyn.app.features.cart.models.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.models.IncrementItemCountRequestBody
import com.cravyn.app.features.cart.models.PlaceOrderRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface CartApi {
    @POST("customer/cart/item")
    suspend fun addItemToCart(
        @Body body: AddItemtoCartRequestBody
    ): Response<ApiResponse<AddItemToCartResponse>>

    @GET("customer/cart/")
    suspend fun getCart(): Response<ApiResponse<CartResponse>>

    @PATCH("customer/cart/item/plus")
    suspend fun incrementItemCount(
        @Body body: IncrementItemCountRequestBody
    ): Response<ApiResponse<CartResponse>>

    @PATCH("customer/cart/item/minus")
    suspend fun decrementItemCount(
        @Body body: DecrementItemCountRequestBody
    ): Response<ApiResponse<CartResponse>>

    @DELETE("customer/cart/item")
    suspend fun deleteItemFromCart(
        @Query("itemId") itemId: String
    ): Response<ApiResponse<CartResponse>>

    @POST("customer/place-order")
    suspend fun placeOrder(
        @Body body: PlaceOrderRequestBody
    ): Response<ApiResponse<Unit>>
}
