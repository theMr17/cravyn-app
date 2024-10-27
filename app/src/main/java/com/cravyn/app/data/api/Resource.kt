package com.cravyn.app.data.api

/**
 * A sealed class representing the state of a resource, typically used to manage
 * UI states for data loading, success, and error scenarios.
 *
 * @param T The type of the data associated with the resource.
 * @property data The data associated with the resource, if available.
 * @property message An optional message, often used for error descriptions.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * Represents a successful resource state, with optional data and message.
     *
     * @param T The type of the data associated with the resource.
     * @param data The data associated with a successful response.
     * @param message An optional message describing the success.
     */
    class Success<T>(
        data: T,
        message: String? = null
    ) : Resource<T>(data = data, message = message ?: "Task completed successfully.")

    /**
     * Represents an error state, with an optional error message.
     *
     * @param T The type of the data associated with the resource.
     * @param message A message describing the error, defaults to a generic error message.
     */
    class Error<T>(
        message: String = "An unknown error occurred."
    ) : Resource<T>(message = message)

    /**
     * Represents a loading state, typically used when a resource is being fetched.
     *
     * @param T The type of the data associated with the resource.
     */
    class Loading<T> : Resource<T>()
}
