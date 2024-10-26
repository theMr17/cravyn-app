package com.cravyn.app.features.auth

import com.cravyn.app.data.api.ApiResponse
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.ForgotPasswordRequestBody
import com.cravyn.app.features.auth.models.ForgotPasswordResponse
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.LoginResponse
import com.cravyn.app.features.auth.models.OtpVerificationRequestBody
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.RegisterResponse
import com.cravyn.app.features.auth.models.ResetPasswordRequestBody
import com.cravyn.app.features.auth.models.ResetPasswordResponse
import com.cravyn.app.features.auth.models.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Interface for managing authentication-related operations.
 *
 * This repository defines methods for user login, logout, registration,
 * password management, and user data persistence.
 */
interface AuthRepository {
    /**
     * Attempts to log in the user with the provided credentials.
     *
     * @param body The login request containing user credentials.
     * @return A response containing the API response with login details.
     */
    suspend fun login(body: LoginRequestBody): Response<ApiResponse<LoginResponse>>

    /**
     * Logs out the current user.
     *
     * @return A response indicating the logout operation's success or failure.
     */
    suspend fun logout(): Response<ApiResponse<Unit>>

    /**
     * Registers a new user with the provided information.
     *
     * @param body The registration request containing user details.
     * @return A response containing the API response with registration details.
     */
    suspend fun register(body: RegisterRequestBody): Response<ApiResponse<RegisterResponse>>

    /**
     * Initiates the forgot password process for the user.
     *
     * @param body The request containing user information to recover the password.
     * @return A response containing the API response for the forgot password process.
     */
    suspend fun forgotPassword(body: ForgotPasswordRequestBody): Response<ApiResponse<ForgotPasswordResponse>>

    /**
     * Verifies the OTP sent to the user during the password recovery process.
     *
     * @param body The request containing the OTP information.
     * @return A response indicating the verification result.
     */
    suspend fun otpVerification(body: OtpVerificationRequestBody): Response<ApiResponse<Unit>>

    /**
     * Resets the user's password to a new one.
     *
     * @param body The request containing the new password and user information.
     * @return A response containing the API response for the password reset.
     */
    suspend fun resetPassword(body: ResetPasswordRequestBody): Response<ApiResponse<ResetPasswordResponse>>

    /**
     * Saves the user data to the database.
     *
     * @param user The user object containing user information to save.
     * @return A flow emitting the result of the save operation as a [Resource].
     */
    fun saveUserToDatabase(user: User): Flow<Resource<Unit>>

    /**
     * Deletes the current user data from the database.
     *
     * @return A flow emitting the result of the delete operation as a [Resource].
     */
    fun deleteUserFromDatabase(): Flow<Resource<Unit>>

    /**
     * Checks whether a user is currently logged in.
     *
     * @return A flow emitting a boolean indicating the login status as a [Resource].
     */
    fun isUserLoggedIn(): Flow<Resource<Boolean>>
}
