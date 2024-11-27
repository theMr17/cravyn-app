package com.cravyn.app.features.search

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.search.models.SearchResponse
import retrofit2.Response

interface SearchRepository {
    suspend fun getSearchedFoodAndRestaurants(search: String): Response<ApiResponse<SearchResponse>>
}
