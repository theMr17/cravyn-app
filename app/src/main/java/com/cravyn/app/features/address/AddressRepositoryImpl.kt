package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.AddressResponse
import com.cravyn.app.features.address.models.CoordinatesResponseItem
import retrofit2.Response
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressApi: AddressApi
) : AddressRepository {
    override suspend fun getCoordinates(address: String): Response<ApiResponse<List<CoordinatesResponseItem>>> {
        return addressApi.getCoordinates(address)
    }

    override suspend fun getAddress(
        latitude: Double,
        longitude: Double
    ): Response<ApiResponse<AddressResponse>> {
        return addressApi.getAddress(latitude, longitude)
    }
}
