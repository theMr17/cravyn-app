package com.cravyn.app.util

import org.json.JSONObject
import retrofit2.Response

object ErrorResponseParserUtil {
    fun <T> getErrorMessage(errorResponse: Response<T>): String {
        return JSONObject(
            errorResponse.errorBody()!!.string()
        ).getString("message")
    }
}
