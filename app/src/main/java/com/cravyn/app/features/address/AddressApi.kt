package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.AddressResponse
import com.cravyn.app.features.address.models.CoordinatesResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApi {
    @GET("geocode/coordinates")
    suspend fun getCoordinates(
        @Query("address") address: String
    ): Response<ApiResponse<List<CoordinatesResponseItem>>>

    @GET("geocode/address")
    suspend fun getAddress(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<ApiResponse<AddressResponse>>
}
