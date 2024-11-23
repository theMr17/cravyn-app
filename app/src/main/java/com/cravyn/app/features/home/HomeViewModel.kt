package com.cravyn.app.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.home.models.RecommendedRestaurantItem
import com.cravyn.app.features.home.models.toRecommendedRestaurantItem
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _recommendedRestaurantsLiveData: MutableLiveData<Resource<List<RecommendedRestaurantItem>>> =
        MutableLiveData()
    val recommendedRestaurantsLiveData: LiveData<Resource<List<RecommendedRestaurantItem>>> get() = _recommendedRestaurantsLiveData

    fun getRecommendedRestaurants(
        lat: Double = 22.6865,
        long: Double = 88.4694,
        minRating: Float = 0.0f,
        sortBy: String = "distance",
        radius: Float = 30.0f,
        descending: Boolean = false,
        limit: Int = 50
    ) {
        viewModelScope.launch {
            _recommendedRestaurantsLiveData.postValue(Resource.Loading())

            val recommendedRestaurantsResponse = homeRepository.getRecommendedRestaurants(
                lat,
                long,
                minRating,
                sortBy,
                radius,
                descending,
                limit
            )

            if (recommendedRestaurantsResponse.isSuccessful) {
                val recommendedRestaurantItems =
                    recommendedRestaurantsResponse.body()?.data?.restaurants?.map {
                        it.toRecommendedRestaurantItem()
                    } ?: emptyList()
                _recommendedRestaurantsLiveData.postValue(
                    Resource.Success(
                        data = recommendedRestaurantItems,
                        message = recommendedRestaurantsResponse.body()?.message
                    )
                )
            } else {
                _recommendedRestaurantsLiveData.postValue(
                    Resource.Error(getErrorMessage(recommendedRestaurantsResponse))
                )
            }
        }
    }
}
