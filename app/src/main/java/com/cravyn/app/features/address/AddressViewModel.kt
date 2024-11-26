package com.cravyn.app.features.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.address.models.RemoveAddressRequestBody
import com.cravyn.app.features.address.models.SaveAddressRequestBody
import com.cravyn.app.features.address.models.SaveAddressResponse
import com.cravyn.app.features.address.models.SavedAddressesResponse
import com.cravyn.app.features.address.models.SearchedAddressResponseItem
import com.cravyn.app.features.address.models.SetDefaultAddressRequestBody
import com.cravyn.app.features.address.models.SetDefaultAddressResponse
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
    private var searchAddressesJob: Job? = null

    private val _searchedAddressListLiveData: MutableLiveData<Resource<List<SearchedAddressResponseItem>>> =
        MutableLiveData()
    val searchedAddressListLiveData: LiveData<Resource<List<SearchedAddressResponseItem>>> get() = _searchedAddressListLiveData

    private val _savedAddressesLiveData: MutableLiveData<Resource<SavedAddressesResponse>> =
        MutableLiveData()
    val savedAddressesLiveData: LiveData<Resource<SavedAddressesResponse>> get() = _savedAddressesLiveData

    private val _defaultAddressLiveData: MutableLiveData<Resource<SavedAddressesResponse>> =
        MutableLiveData()
    val defaultAddressLiveData: LiveData<Resource<SavedAddressesResponse>> get() = _defaultAddressLiveData

    private val _saveAddressLiveData: MutableLiveData<Resource<SaveAddressResponse>> =
        MutableLiveData()
    val saveAddressLiveData: LiveData<Resource<SaveAddressResponse>> get() = _saveAddressLiveData

    private val _removeAddressLiveData: MutableLiveData<Resource<Unit>> =
        MutableLiveData()
    val removeAddressLiveData: LiveData<Resource<Unit>> get() = _removeAddressLiveData

    private val _setDefaultAddressLiveData: MutableLiveData<Resource<SetDefaultAddressResponse>> =
        MutableLiveData()
    val setDefaultAddressLiveData: LiveData<Resource<SetDefaultAddressResponse>> get() = _setDefaultAddressLiveData

    fun searchAddresses(address: String) {
        searchAddressesJob?.cancel()
        searchAddressesJob = viewModelScope.launch {
            delay(800)

            _searchedAddressListLiveData.postValue(Resource.Loading())

            val response = addressRepository.searchAddresses(address)

            if (response.isSuccessful) {
                _searchedAddressListLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _searchedAddressListLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }

    fun getSavedAddresses() {
        viewModelScope.launch {
            _savedAddressesLiveData.postValue(Resource.Loading())

            val response = addressRepository.getSavedAddresses()

            if (response.isSuccessful) {
                _savedAddressesLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _savedAddressesLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }

    fun getDefaultAddress() {
        viewModelScope.launch {
            _defaultAddressLiveData.postValue(Resource.Loading())

            val response = addressRepository.getDefaultAddress()

            if (response.isSuccessful) {
                _defaultAddressLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _defaultAddressLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }

    fun saveAddress(latitude: Double, longitude: Double, displayAddress: String) {
        viewModelScope.launch {
            _saveAddressLiveData.postValue(Resource.Loading())

            val saveAddressRequestBody = SaveAddressRequestBody(
                latitude = latitude,
                longitude = longitude,
                displayAddress = displayAddress
            )

            val response = addressRepository.saveAddress(saveAddressRequestBody)

            if (response.isSuccessful) {
                _saveAddressLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _saveAddressLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }

    fun removeAddress(removeAddressRequestBody: RemoveAddressRequestBody) {
        viewModelScope.launch {
            _removeAddressLiveData.postValue(Resource.Loading())

            val response = addressRepository.removeAddress(removeAddressRequestBody)

            if (response.isSuccessful) {
                _removeAddressLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _removeAddressLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }

    fun setDefaultAddress(setDefaultAddressRequestBody: SetDefaultAddressRequestBody) {
        viewModelScope.launch {
            _setDefaultAddressLiveData.postValue(Resource.Loading())

            val response = addressRepository.setDefaultAddress(setDefaultAddressRequestBody)

            if (response.isSuccessful) {
                _setDefaultAddressLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _setDefaultAddressLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }
}
