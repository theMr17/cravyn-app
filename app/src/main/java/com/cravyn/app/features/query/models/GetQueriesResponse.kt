package com.cravyn.app.features.query.models


import com.google.gson.annotations.SerializedName

data class GetQueriesResponse(
    @SerializedName("customerQueries")
    val customerQueries: List<CustomerQuery>
) {
    data class CustomerQuery(
        @SerializedName("answer")
        val answer: String?,
        @SerializedName("customer_id")
        val customerId: String,
        @SerializedName("manager_id")
        val managerId: String?,
        @SerializedName("manager_name")
        val managerName: String?,
        @SerializedName("query_id")
        val queryId: String,
        @SerializedName("question")
        val question: String
    )
}
