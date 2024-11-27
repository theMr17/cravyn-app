package com.cravyn.app.features.search

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.search.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search")
    suspend fun getSearchedFoodAndRestaurant(
        @Query("search") search: String
    ): Response<ApiResponse<SearchResponse>>
}
