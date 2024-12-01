package com.cravyn.app.features.query

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.query.models.GetQueriesResponse
import com.cravyn.app.features.query.models.RaiseQueryRequestBody
import retrofit2.Response

interface QueryRepository {
    suspend fun getQueries(): Response<ApiResponse<GetQueriesResponse>>

    suspend fun raiseQuery(raiseQueryRequestBody: RaiseQueryRequestBody): Response<ApiResponse<Unit>>
}
