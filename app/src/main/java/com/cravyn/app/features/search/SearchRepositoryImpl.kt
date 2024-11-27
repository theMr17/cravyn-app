package com.cravyn.app.features.search

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.search.models.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun getSearchedFoodAndRestaurants(search: String): Response<ApiResponse<SearchResponse>> {
        return searchApi.getSearchedFoodAndRestaurant(search)
    }
}
