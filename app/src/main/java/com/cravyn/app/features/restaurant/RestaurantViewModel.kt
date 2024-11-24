package com.cravyn.app.features.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.restaurant.models.RestaurantMenuResponse
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    private val _restaurantMenuLiveData: MutableLiveData<Resource<RestaurantMenuResponse>> =
        MutableLiveData()
    val restaurantMenuLiveData: LiveData<Resource<RestaurantMenuResponse>> get() = _restaurantMenuLiveData

    fun getRestaurantMenu(restaurantId: String, limit: Int = 50) {
        viewModelScope.launch {
            _restaurantMenuLiveData.postValue(Resource.Loading())

            val restaurantMenuResponse = restaurantRepository.getRestaurantMenu(
                restaurantId,
                limit
            )

            if (restaurantMenuResponse.isSuccessful) {
                _restaurantMenuLiveData.postValue(
                    Resource.Success(
                        data = restaurantMenuResponse.body()!!.data,
                        message = restaurantMenuResponse.body()?.message
                    )
                )
            } else {
                _restaurantMenuLiveData.postValue(
                    Resource.Error(getErrorMessage(restaurantMenuResponse))
                )
            }
        }
    }
}
