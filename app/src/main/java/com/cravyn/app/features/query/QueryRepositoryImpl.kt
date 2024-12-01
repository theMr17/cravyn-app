package com.cravyn.app.features.query

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.query.models.GetQueriesResponse
import com.cravyn.app.features.query.models.RaiseQueryRequestBody
import retrofit2.Response
import javax.inject.Inject

class QueryRepositoryImpl @Inject constructor(
    private val queryApi: QueryApi,
) : QueryRepository {
    override suspend fun getQueries(): Response<ApiResponse<GetQueriesResponse>> {
        return queryApi.getQueries()
    }

    override suspend fun raiseQuery(raiseQueryRequestBody: RaiseQueryRequestBody): Response<ApiResponse<Unit>> {
        return queryApi.raiseQuery(raiseQueryRequestBody)
    }
}
