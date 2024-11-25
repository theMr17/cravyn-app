package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.SearchedAddressResponseItem
import retrofit2.Response
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressApi: AddressApi
) : AddressRepository {
    override suspend fun searchAddresses(address: String): Response<ApiResponse<List<SearchedAddressResponseItem>>> {
        return addressApi.searchAddresses(address)
    }
}
