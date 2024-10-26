package com.cravyn.app.features.auth

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.features.auth.models.DeleteAccountRequestBody
import com.cravyn.app.features.auth.models.ForgotPasswordRequestBody
import com.cravyn.app.features.auth.models.ForgotPasswordResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.OtpVerificationRequestBody
import com.cravyn.app.features.auth.models.RefreshAccessTokenRequestBody
import com.cravyn.app.features.auth.models.RefreshAccessTokenResponse
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.ResetPasswordRequestBody
import com.cravyn.app.features.auth.models.ResetPasswordResponse
import com.cravyn.app.features.auth.models.UpdateAccountRequestBody
import com.cravyn.app.features.auth.models.UpdateAccountResponse
import com.cravyn.app.features.auth.models.UpdateProfileImageRequestBody
import com.cravyn.app.features.auth.models.UpdateProfileImageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interface defining the authentication API endpoints.
 *
 * This interface provides methods for user registration, login, logout,
 * account management, password recovery, and token refreshing.
 */
interface AuthApi {
    /**
     * Logs in a user with the provided credentials.
     *
     * @param body The login request containing user credentials.
     * @return A response containing the API response with login details.
     */
    @POST("customer/login")
    suspend fun login(
        @Body body: LoginRequestBody
    ): Response<ApiResponse<LoginResponse>>

    /**
     * Logs out the current user.
     *
     * @return A response indicating the logout operation's success or failure.
     */
    @POST("customer/logout")
    suspend fun logout(): Response<ApiResponse<Unit>>

    /**
     * Registers a new user with the provided information.
     *
     * @param body The registration request containing user details.
     * @return A response containing the API response with registration details.
     */
    @POST("customer/register")
    suspend fun register(
        @Body body: RegisterRequestBody
    ): Response<ApiResponse<RegisterResponse>>

    /**
     * Updates the account information for the current user.
     *
     * @param body The request containing updated account information.
     * @return A response containing the API response for the account update.
     */
    @PATCH("customer")
    suspend fun updateAccount(
        @Body body: UpdateAccountRequestBody
    ): Response<ApiResponse<UpdateAccountResponse>>

    /**
     * Deletes the current user's account.
     *
     * @param body The request containing user identification for deletion.
     * @return A response indicating the success or failure of the delete operation.
     */
    @DELETE("customer")
    suspend fun deleteAccount(
        @Body body: DeleteAccountRequestBody
    ): Response<ApiResponse<Unit>>

    /**
     * Updates the profile image of the current user.
     *
     * @param body The request containing the new profile image data.
     * @return A response containing the API response for the profile image update.
     */
    @PATCH("customer/profile-image")
    suspend fun updateProfileImage(
        @Body body: UpdateProfileImageRequestBody
    ): Response<ApiResponse<UpdateProfileImageResponse>>

    /**
     * Initiates the forgot password process for the user.
     *
     * @param userType The type of user (default is "customer").
     * @param body The request containing user information to recover the password.
     * @return A response containing the API response for the forgot password process.
     */
    @POST("forgot-password")
    suspend fun forgotPassword(
        @Query("userType") userType: String = "customer",
        @Body body: ForgotPasswordRequestBody
    ): Response<ApiResponse<ForgotPasswordResponse>>

    /**
     * Verifies the OTP sent to the user during the password recovery process.
     *
     * @param userType The type of user (default is "customer").
     * @param body The request containing the OTP information.
     * @return A response indicating the verification result.
     */
    @POST("forgot-password/verify-otp")
    suspend fun otpVerification(
        @Query("userType") userType: String = "customer",
        @Body body: OtpVerificationRequestBody
    ): Response<ApiResponse<Unit>>

    /**
     * Resets the user's password to a new one.
     *
     * @param userType The type of user (default is "customer").
     * @param body The request containing the new password and user information.
     * @return A response containing the API response for the password reset.
     */
    @PATCH("forgot-password/reset-password")
    suspend fun resetPassword(
        @Query("userType") userType: String = "customer",
        @Body body: ResetPasswordRequestBody
    ): Response<ApiResponse<ResetPasswordResponse>>

    /**
     * Refreshes the access token using the provided refresh token.
     *
     * @param body The request containing the refresh token.
     * @return A Call object containing the API response with the new access token.
     */
    @POST("customer/refresh-token")
    fun refreshAccessToken(
        @Body body: RefreshAccessTokenRequestBody
    ): Call<ApiResponse<RefreshAccessTokenResponse>>
}
