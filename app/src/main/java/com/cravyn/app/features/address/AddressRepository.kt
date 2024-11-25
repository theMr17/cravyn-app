package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.SearchedAddressResponseItem
import retrofit2.Response

interface AddressRepository {
    suspend fun searchAddresses(
        address: String
    ): Response<ApiResponse<List<SearchedAddressResponseItem>>>
}
