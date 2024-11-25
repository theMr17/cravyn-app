package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.RemoveAddressRequestBody
import com.cravyn.app.features.address.models.SaveAddressRequestBody
import com.cravyn.app.features.address.models.SaveAddressResponse
import com.cravyn.app.features.address.models.SavedAddressesResponse
import com.cravyn.app.features.address.models.SearchedAddressResponseItem
import com.cravyn.app.features.address.models.SetDefaultAddressRequestBody
import com.cravyn.app.features.address.models.SetDefaultAddressResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApi {
    @GET("geocode/coordinates")
    suspend fun searchAddresses(
        @Query("address") address: String
    ): Response<ApiResponse<List<SearchedAddressResponseItem>>>

    @GET("customer/address")
    suspend fun getSavedAddresses(): Response<ApiResponse<SavedAddressesResponse>>

    @GET("customer/address")
    suspend fun getDefaultAddress(
        @Query("isDefault") isDefault: Boolean,
    ): Response<ApiResponse<SavedAddressesResponse>>

    @POST("customer/address")
    suspend fun saveAddress(
        @Body saveAddressRequestBody: SaveAddressRequestBody
    ): Response<ApiResponse<SaveAddressResponse>>

    @DELETE("customer/address")
    suspend fun removeAddress(
        @Body removeAddressRequestBody: RemoveAddressRequestBody
    ): Response<ApiResponse<Unit>>

    @PATCH("customer/address")
    suspend fun setDefaultAddress(
        @Body setDefaultAddressRequestBody: SetDefaultAddressRequestBody
    ): Response<ApiResponse<SetDefaultAddressResponse>>
}
