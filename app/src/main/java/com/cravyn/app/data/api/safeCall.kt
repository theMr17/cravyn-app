package com.cravyn.app.data.api

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

inline fun <reified T> safeCall(
    execute: () -> Response<ApiResponse<T>>
): Response<ApiResponse<T>> {
    return try {
        execute()
    } catch (e: Exception) {
        e.printStackTrace()
        // Create a failed response object to handle exceptions.
        Response.error(
            500,
            """{"message": "An error occurred: ${e.localizedMessage}"}"""
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }
}
