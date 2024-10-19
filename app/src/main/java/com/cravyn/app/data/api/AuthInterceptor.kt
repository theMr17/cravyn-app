package com.cravyn.app.data.api

import com.cravyn.app.features.auth.JwtTokenRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var accessToken = ""
        runBlocking {
            jwtTokenRepository.getAccessTokenFromDatabase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> accessToken = result.data ?: ""
                    else -> {}
                }
            }
        }

        if (accessToken.isBlank()) {
            return chain.proceed(chain.request())
        }

        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(request)
    }
}
