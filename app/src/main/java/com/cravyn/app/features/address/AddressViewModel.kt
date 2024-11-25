package com.cravyn.app.features.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.address.models.CoordinatesResponseItem
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
    private val _coordinatesListLiveData: MutableLiveData<Resource<List<CoordinatesResponseItem>>> =
        MutableLiveData()
    val coordinatesListLiveData: LiveData<Resource<List<CoordinatesResponseItem>>> get() = _coordinatesListLiveData

    private var getCoordinatesJob: Job? = null

    fun getCoordinates(address: String) {
        getCoordinatesJob?.cancel()
        getCoordinatesJob = viewModelScope.launch {
            delay(800)
            _coordinatesListLiveData.postValue(Resource.Loading())

            val coordinatesListResponse = addressRepository.getCoordinates(address)

            if (coordinatesListResponse.isSuccessful) {
                _coordinatesListLiveData.postValue(
                    Resource.Success(
                        data = coordinatesListResponse.body()!!.data,
                        message = coordinatesListResponse.body()?.message
                    )
                )
            } else {
                _coordinatesListLiveData.postValue(
                    Resource.Error(getErrorMessage(coordinatesListResponse))
                )
            }
        }
    }
}
