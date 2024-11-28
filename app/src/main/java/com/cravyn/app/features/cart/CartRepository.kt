package com.cravyn.app.features.cart

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import com.cravyn.app.features.cart.model.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.model.DeleteItemFomCartRequestBody
import com.cravyn.app.features.cart.model.GetCartResponse
import com.cravyn.app.features.cart.model.IncrementItemCountRequestBody
import retrofit2.Response

interface CartRepository {

    suspend fun addItemtoCart(body: AddItemtoCartRequestBody): Response<ApiResponse<AddItemToCartResponse>>

    suspend fun getCart(): Response<ApiResponse<GetCartResponse>>

    suspend fun incrementItemCount(body: IncrementItemCountRequestBody): Response<ApiResponse<GetCartResponse>>

    suspend fun decrementItemCount(body: DecrementItemCountRequestBody): Response<ApiResponse<GetCartResponse>>

    suspend fun deleteItemFromCart(body: DeleteItemFomCartRequestBody): Response<ApiResponse<GetCartResponse>>

}