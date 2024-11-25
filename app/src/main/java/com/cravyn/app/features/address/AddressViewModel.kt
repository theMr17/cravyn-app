package com.cravyn.app.features.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.address.models.SearchedAddressResponseItem
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {
    private val _searchedAddressListLiveData: MutableLiveData<Resource<List<SearchedAddressResponseItem>>> =
        MutableLiveData()
    val searchedAddressListLiveData: LiveData<Resource<List<SearchedAddressResponseItem>>> get() = _searchedAddressListLiveData

    private var searchAddressesJob: Job? = null

    fun searchAddresses(address: String) {
        searchAddressesJob?.cancel()
        searchAddressesJob = viewModelScope.launch {
            delay(800)
            _searchedAddressListLiveData.postValue(Resource.Loading())

            val coordinatesListResponse = addressRepository.searchAddresses(address)

            if (coordinatesListResponse.isSuccessful) {
                _searchedAddressListLiveData.postValue(
                    Resource.Success(
                        data = coordinatesListResponse.body()!!.data,
                        message = coordinatesListResponse.body()?.message
                    )
                )
            } else {
                _searchedAddressListLiveData.postValue(
                    Resource.Error(getErrorMessage(coordinatesListResponse))
                )
            }
        }
    }
}
