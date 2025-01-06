package com.cravyn.app.features.address

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.safeCall
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
        return safeCall {
            addressApi.searchAddresses(address)
        }
    }

    override suspend fun getSavedAddresses(): Response<ApiResponse<SavedAddressesResponse>> {
        return safeCall {
            addressApi.getSavedAddresses()
        }
    }

    override suspend fun getDefaultAddress(): Response<ApiResponse<SavedAddressesResponse>> {
        return safeCall {
            addressApi.getDefaultAddress(isDefault = true)
        }
    }

    override suspend fun saveAddress(
        saveAddressRequestBody: SaveAddressRequestBody
    ): Response<ApiResponse<SaveAddressResponse>> {
        return safeCall {
            addressApi.saveAddress(saveAddressRequestBody)
        }
    }

    override suspend fun removeAddress(
        addressId: String
    ): Response<ApiResponse<Unit>> {
        return safeCall {
            addressApi.removeAddress(addressId)
        }
    }

    override suspend fun setDefaultAddress(
        setDefaultAddressRequestBody: SetDefaultAddressRequestBody
    ): Response<ApiResponse<SetDefaultAddressResponse>> {
        return safeCall {
            addressApi.setDefaultAddress(setDefaultAddressRequestBody)
        }
    }
}
