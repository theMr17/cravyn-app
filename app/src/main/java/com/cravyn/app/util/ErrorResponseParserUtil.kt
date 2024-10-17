package com.cravyn.app.util

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

/**
 * Utility object for parsing error responses from Retrofit.
 *
 * This utility provides a function to extract error messages from
 * HTTP error responses returned by the Retrofit library.
 */
object ErrorResponseParserUtil {
    private const val TAG = "ErrorResponseParserUtil"

    /**
     * Extracts an error message from a Retrofit [Response] object.
     *
     * This method retrieves the error body from the response, parses it as a JSON object,
     * and then extracts the value associated with the key `"message"`.
     *
     * @param errorResponse The Retrofit response containing the error body.
     * @return The error message as a [String], or a default message if an error occurs.
     */
    fun <T> getErrorMessage(errorResponse: Response<T>): String {
        return try {
            val errorBody = errorResponse.errorBody()?.string()
            if (errorBody != null) {
                JSONObject(errorBody).getString("message")
            } else {
                Log.e(TAG, "Error body is null.")
                "An unexpected error occurred."
            }
        } catch (e: JSONException) {
            Log.e(TAG, "Failed to parse error message: ${e.message}")
            "An error occurred. Please try again."
        } catch (e: IOException) {
            Log.e(TAG, "Failed to read error body: ${e.message}")
            "An error occurred. Please try again."
        }
    }
}
