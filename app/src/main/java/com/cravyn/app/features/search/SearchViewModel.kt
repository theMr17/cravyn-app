package com.cravyn.app.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.search.models.SearchResponse
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
):ViewModel() {
    private var searchJob: Job? = null
    private val _searchedFoodAndRestaurantLivedata: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    val searchedFoodAndRestaurantLivedata:LiveData<Resource<SearchResponse>> get() = _searchedFoodAndRestaurantLivedata

    fun getSearchedFoodAndRestaurants(search:String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000L)
            _searchedFoodAndRestaurantLivedata.postValue(Resource.Loading())

            val searchedFoodResponse = searchRepository.getSearchedFoodAndRestaurants(search)

            if(searchedFoodResponse.isSuccessful) {
                _searchedFoodAndRestaurantLivedata.postValue(Resource.Success(
                    data = searchedFoodResponse.body()!!.data,
                    message = searchedFoodResponse.body()?.message
                ))
            } else {
                _searchedFoodAndRestaurantLivedata.postValue(
                    Resource.Error(getErrorMessage(searchedFoodResponse))
                )
            }
        }
    }


}