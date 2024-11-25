package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.AddressResponse
import com.cravyn.app.features.address.models.CoordinatesResponseItem
import retrofit2.Response

interface AddressRepository {
    suspend fun getCoordinates(
        address: String
    ): Response<ApiResponse<List<CoordinatesResponseItem>>>

    suspend fun getAddress(
        latitude: Double,
        longitude: Double,
    ): Response<ApiResponse<AddressResponse>>
}
