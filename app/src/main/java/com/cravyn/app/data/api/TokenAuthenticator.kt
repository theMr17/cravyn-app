package com.cravyn.app.data.api

import com.cravyn.app.BuildConfig
import com.cravyn.app.features.auth.AuthApi
import com.cravyn.app.features.auth.JwtTokenRepository
import com.cravyn.app.features.auth.models.RefreshAccessTokenRequestBody
import com.cravyn.app.features.auth.models.RefreshAccessTokenResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshAccessTokenResponse = getNewAccessAndRefreshTokens()
        if (refreshAccessTokenResponse == null) {
            // Log out user.
        }
        return refreshAccessTokenResponse?.let {
            jwtTokenRepository.updateAccessAndRefreshTokenOnDatabase(
                accessToken = refreshAccessTokenResponse.accessToken,
                refreshToken = refreshAccessTokenResponse.refreshToken
            )
            response.request.newBuilder().header("Authorization", "Bearer ${it.accessToken}")
                .build()
        }
    }

    private fun getNewAccessAndRefreshTokens(): RefreshAccessTokenResponse? {
        lateinit var refreshAccessTokenRequestBody: RefreshAccessTokenRequestBody

        runBlocking {
            jwtTokenRepository.getRefreshTokenFromDatabase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        refreshAccessTokenRequestBody = RefreshAccessTokenRequestBody(
                            refreshToken = result.data ?: ""
                        )
                    }

                    else -> {}
                }
            }
        }

        try {
            val call = Retrofit.Builder().baseUrl("${BuildConfig.BASE_URL}/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AuthApi::class.java)
                .refreshAccessToken(refreshAccessTokenRequestBody)

            val refreshAccessTokenResponse = call.execute()

            return refreshAccessTokenResponse.body()?.data
        } catch (e: Exception) {
            return null
        }
    }
}
