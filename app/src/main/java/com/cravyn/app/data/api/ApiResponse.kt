package com.cravyn.app.data.api

/**
 * A generic data class that represents the API response.
 *
 * @param T The type of the data returned by the API.
 * @property statusCode The HTTP status code of the response.
 * @property data The actual data returned by the API, of type [T].
 * @property message A message from the API, usually detailing the status or errors.
 * @property success Indicates whether the API request was successful.
 */
data class ApiResponse<T>(
    val statusCode: Int,
    val data: T,
    val message: String,
    val success: Boolean
)
