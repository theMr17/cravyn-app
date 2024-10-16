package com.cravyn.app.data.api

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T, message: String? = null) : Resource<T>(data = data, message = message)

    class Error<T>(
        message: String = "An unknown error occurred."
    ) : Resource<T>(message = message)

    class Loading<T> : Resource<T>()
}
