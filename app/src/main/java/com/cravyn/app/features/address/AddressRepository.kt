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

interface AddressRepository {
    suspend fun searchAddresses(
        address: String
    ): Response<ApiResponse<List<SearchedAddressResponseItem>>>

    suspend fun getSavedAddresses(): Response<ApiResponse<SavedAddressesResponse>>

    suspend fun getDefaultAddress(): Response<ApiResponse<SavedAddressesResponse>>

    suspend fun saveAddress(
        saveAddressRequestBody: SaveAddressRequestBody
    ): Response<ApiResponse<SaveAddressResponse>>

    suspend fun removeAddress(
        removeAddressRequestBody: RemoveAddressRequestBody
    ): Response<ApiResponse<Unit>>

    suspend fun setDefaultAddress(
        setDefaultAddressRequestBody: SetDefaultAddressRequestBody
    ): Response<ApiResponse<SetDefaultAddressResponse>>
}
