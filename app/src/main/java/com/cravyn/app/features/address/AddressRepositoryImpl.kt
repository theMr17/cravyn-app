package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.address.models.SaveAddressRequestBody
import com.cravyn.app.features.address.models.SaveAddressResponse
import com.cravyn.app.features.address.models.SavedAddressesResponse
import com.cravyn.app.features.address.models.SearchedAddressResponseItem
import com.cravyn.app.features.address.models.SetDefaultAddressRequestBody
import com.cravyn.app.features.address.models.SetDefaultAddressResponse
import retrofit2.Response
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressApi: AddressApi
) : AddressRepository {
    override suspend fun searchAddresses(
        address: String
    ): Response<ApiResponse<List<SearchedAddressResponseItem>>> {
        return addressApi.searchAddresses(address)
    }

    override suspend fun getSavedAddresses(): Response<ApiResponse<SavedAddressesResponse>> {
        return addressApi.getSavedAddresses()
    }

    override suspend fun getDefaultAddress(): Response<ApiResponse<SavedAddressesResponse>> {
        return addressApi.getDefaultAddress(isDefault = true)
    }

    override suspend fun saveAddress(
        saveAddressRequestBody: SaveAddressRequestBody
    ): Response<ApiResponse<SaveAddressResponse>> {
        return addressApi.saveAddress(saveAddressRequestBody)
    }

    override suspend fun removeAddress(
        addressId: String
    ): Response<ApiResponse<Unit>> {
        return addressApi.removeAddress(addressId)
    }

    override suspend fun setDefaultAddress(
        setDefaultAddressRequestBody: SetDefaultAddressRequestBody
    ): Response<ApiResponse<SetDefaultAddressResponse>> {
        return addressApi.setDefaultAddress(setDefaultAddressRequestBody)
    }
}
