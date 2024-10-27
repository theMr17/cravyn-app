package com.cravyn.app.data.api

import com.cravyn.app.features.auth.JwtTokenRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * An interceptor that adds an authorization header with a JWT token to API requests.
 *
 * @property jwtTokenRepository The repository for retrieving and managing JWT tokens.
 */
class AuthInterceptor @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository
) : Interceptor {
    /**
     * Intercepts the outgoing HTTP request and adds the Authorization header if a valid
     * access token is available. If no token is found, the request proceeds without modification.
     *
     * @param chain The chain of interceptors, used to proceed with the original or modified request.
     * @return The HTTP [Response] obtained after proceeding with the request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Retrieve the access token from the repository; runBlocking used to ensure synchronous execution.
        var accessToken = ""
        runBlocking {
            jwtTokenRepository.getAccessTokenFromDatabase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> accessToken = result.data ?: ""
                    else -> {}
                }
            }
        }

        // Proceed with the request without modification if the access token is blank.
        if (accessToken.isBlank()) {
            return chain.proceed(chain.request())
        }

        // Modify the request to add the Authorization header if a valid token is available.
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(request)
    }
}
