package com.cravyn.app.data

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)

    class Error<T>(
        message: String = "An unknown error occurred.",
        statusCode: Int? = null
    ) : Resource<T>(message = message, statusCode = statusCode)

    class Loading<T> : Resource<T>()
}
