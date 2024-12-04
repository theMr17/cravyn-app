package com.cravyn.app.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.profile.models.ProfileResponse
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _profileLiveData: MutableLiveData<Resource<ProfileResponse>> =
        MutableLiveData()
    val profileLiveData: LiveData<Resource<ProfileResponse>> get() = _profileLiveData

    fun getProfile() {
        viewModelScope.launch {
            _profileLiveData.postValue(Resource.Loading())

            val response = profileRepository.getProfile()

            if (response.isSuccessful) {
                _profileLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _profileLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }
}
