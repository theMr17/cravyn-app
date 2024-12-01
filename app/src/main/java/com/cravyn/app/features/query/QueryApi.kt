package com.cravyn.app.features.query

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.query.models.GetQueriesResponse
import com.cravyn.app.features.query.models.RaiseQueryRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QueryApi {
    @GET("customer/query")
    suspend fun getQueries(): Response<ApiResponse<GetQueriesResponse>>

    @POST("customer/query")
    suspend fun raiseQuery(
        @Body raiseQueryRequestBody: RaiseQueryRequestBody
    ): Response<ApiResponse<Unit>>
}
