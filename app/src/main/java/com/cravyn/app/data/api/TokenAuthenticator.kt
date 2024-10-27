package com.cravyn.app.data.api

import android.util.Log
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

/** Tag for identifying the [TokenAuthenticator] in logs. */
private const val TAG_TOKEN_AUTHENTICATOR = "TOKEN_AUTHENTICATOR"

/**
 * An authenticator that handles token refreshes upon receiving an authentication error (HTTP 401).
 * This class attempts to refresh the access token using the refresh token. If the refresh token has expired,
 * the user is logged out.
 *
 * @property jwtTokenRepository Repository for managing JWT access and refresh tokens.
 */
class TokenAuthenticator @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository
) : Authenticator {
    /**
     * Called when an authentication challenge is received. Attempts to refresh the access token
     * and update the request with a new token if successful. Logs out the user if the refresh fails.
     *
     * @param route The route that produced the authentication challenge.
     * @param response The response that triggered the authenticator.
     * @return A new [Request] with an updated access token, or null if authentication fails.
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshAccessTokenResponse = getNewAccessAndRefreshTokens()
        if (refreshAccessTokenResponse == null) {
            // TODO: Log out user when refresh token expires, and navigate to login screen.
            Log.d(TAG_TOKEN_AUTHENTICATOR, "Refresh token expired, logout user.")
        }
        return refreshAccessTokenResponse?.let {
            // Update tokens in the repository and proceed with the request using the new access token.
            jwtTokenRepository.updateAccessAndRefreshTokenOnDatabase(
                accessToken = refreshAccessTokenResponse.accessToken,
                refreshToken = refreshAccessTokenResponse.refreshToken
            )
            response.request.newBuilder().header("Authorization", "Bearer ${it.accessToken}")
                .build()
        }
    }

    /**
     * Attempts to retrieve new access and refresh tokens from the server using the stored refresh token.
     *
     * @return A [RefreshAccessTokenResponse] containing the new tokens if successful, or null if an error occurs.
     */
    private fun getNewAccessAndRefreshTokens(): RefreshAccessTokenResponse? {
        // Retrieve the current refresh token from the database.
        val refreshToken = runBlocking {
            var token = ""
            jwtTokenRepository.getRefreshTokenFromDatabase().collectLatest { result ->
                if (result is Resource.Success) {
                    token = result.data ?: ""
                }
            }
            token
        }

        // If no refresh token is available, return null to indicate failure to refresh.
        if (refreshToken.isBlank()) return null

        return try {
            // Create an instance of the AuthApi using Retrofit for network requests.
            val authApi = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi::class.java)

            val response = authApi.refreshAccessToken(RefreshAccessTokenRequestBody(refreshToken))
                .execute()

            // Return the response data containing the new tokens, or null if the response is empty.
            response.body()?.data
        } catch (e: Exception) {
            Log.e(TAG_TOKEN_AUTHENTICATOR, "Failed to refresh tokens", e)
            null
        }
    }
}
