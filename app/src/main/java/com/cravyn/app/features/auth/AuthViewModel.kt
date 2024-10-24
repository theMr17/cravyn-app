package com.cravyn.app.features.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.ForgotPasswordRequestBody
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.OtpVerificationRequestBody
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.ResetPasswordRequestBody
import com.cravyn.app.features.auth.models.User
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Tag for identifying the [AuthViewModel] in logs. */
private const val TAG_AUTH_VIEW_MODEL = "AUTH_VIEW_MODEL"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isUserLoggedInLiveData: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val isUserLoggedInLiveData: LiveData<Resource<Boolean>> get() = _isUserLoggedInLiveData

    private val _loginLiveData: MutableLiveData<Resource<User>> = MutableLiveData()
    val loginLiveData: LiveData<Resource<User>> get() = _loginLiveData

    private val _registerLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val registerLiveData: LiveData<Resource<Unit>> get() = _registerLiveData

    private val _logoutLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val logoutLiveData: LiveData<Resource<Unit>> get() = _logoutLiveData

    private val _forgotPasswordLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val forgotPasswordLiveData: LiveData<Resource<Unit>> get() = _forgotPasswordLiveData

    private val _otpVerificationLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val otpVerificationLiveData: LiveData<Resource<Unit>> get() = _otpVerificationLiveData

    private val _resetPasswordLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val resetPasswordLiveData: LiveData<Resource<Unit>> get() = _resetPasswordLiveData

    fun isUserLoggedIn() {
        viewModelScope.launch {
            authRepository.isUserLoggedIn().collectLatest {
                when (it) {
                    is Resource.Error -> _isUserLoggedInLiveData.postValue(Resource.Error(message = "An error occurred while fetching the logged in user."))
                    is Resource.Loading -> _isUserLoggedInLiveData.postValue(Resource.Loading())
                    is Resource.Success -> _isUserLoggedInLiveData.postValue(
                        Resource.Success(
                            data = it.data ?: false, message = it.message
                        )
                    )
                }
            }
        }
    }

    /**
     * Attempts to log in the user with the provided [email] and [password].
     *
     * If the login is successful, the user's details are saved to the database,
     * and a success resource is posted to [loginLiveData].
     * If unsuccessful, an error resource is posted.
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginLiveData.postValue(Resource.Loading())

            val loginRequestBody = LoginRequestBody(email, password)
            val response = authRepository.login(loginRequestBody)
            val responseBody = response.body()

            if (response.isSuccessful && responseBody?.data?.customer != null) {
                val customer = responseBody.data.customer
                val user = User(
                    id = customer.id,
                    dateOfBirth = customer.dateOfBirth,
                    emailAddress = customer.emailAddress,
                    name = customer.name,
                    phoneNumber = customer.phoneNumber,
                    profileImageUrl = customer.profileImageUrl,
                    accessToken = responseBody.data.accessToken,
                    refreshToken = responseBody.data.refreshToken
                )

                saveUserToDatabase(user)

                _loginLiveData.postValue(
                    Resource.Success(
                        data = user,
                        message = responseBody.message
                    )
                )
            } else {
                val errorMessage = if (response.errorBody() == null) {
                    "Login failed: Something went wrong, please try again."
                } else {
                    getErrorMessage(response)
                }
                _loginLiveData.postValue(Resource.Error(errorMessage))
                Log.e(
                    TAG_AUTH_VIEW_MODEL,
                    "Login failed with status code: ${response.code()}. Error: $errorMessage"
                )
            }
        }
    }

    fun register(
        name: String,
        emailAddress: String,
        phoneNumber: String,
        dateOfBirth: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            _registerLiveData.postValue(Resource.Loading())
            val registerRequestBody = RegisterRequestBody(
                name,
                phoneNumber,
                emailAddress,
                dateOfBirth,
                password,
                confirmPassword
            )
            val registerResponse = authRepository.register(registerRequestBody)

            if (registerResponse.isSuccessful) {
                _registerLiveData.postValue(
                    Resource.Success(data = Unit, message = registerResponse.body()?.message)
                )
            } else {
                _registerLiveData.postValue(
                    Resource.Error(getErrorMessage(registerResponse))
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _logoutLiveData.postValue(Resource.Loading())
            val logoutResponse = authRepository.logout()

            if (logoutResponse.isSuccessful) {
                deleteUserFromDatabase()

                _logoutLiveData.postValue(
                    Resource.Success(data = Unit, message = logoutResponse.body()?.message)
                )
            } else {
                _logoutLiveData.postValue(
                    Resource.Error(getErrorMessage(logoutResponse))
                )
            }
        }
    }

    private suspend fun saveUserToDatabase(user: User) {
        authRepository.saveUserToDatabase(user).collectLatest { result ->
            when (result) {
                is Resource.Loading -> Log.d(TAG_AUTH_VIEW_MODEL, "Adding user to database.")
                is Resource.Success -> Log.d(TAG_AUTH_VIEW_MODEL, "User added successfully.")
                is Resource.Error -> Log.e(TAG_AUTH_VIEW_MODEL, "Failed to add user to database.")
            }
        }
    }

    private suspend fun deleteUserFromDatabase() {
        authRepository.deleteUserFromDatabase().collectLatest { result ->
            when (result) {
                is Resource.Loading -> Log.d(TAG_AUTH_VIEW_MODEL, "Deleting user from database.")
                is Resource.Success -> Log.d(TAG_AUTH_VIEW_MODEL, "Deleted user successfully.")
                is Resource.Error -> Log.e(
                    TAG_AUTH_VIEW_MODEL,
                    "Failed to delete user from database."
                )
            }
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPasswordLiveData.postValue(Resource.Loading())
            val forgotPasswordRequestBody = ForgotPasswordRequestBody(email)
            val forgetPasswordResponse = authRepository.forgotPassword(forgotPasswordRequestBody)

            if (forgetPasswordResponse.isSuccessful) {
                _forgotPasswordLiveData.postValue(
                    Resource.Success(data = Unit, message = forgetPasswordResponse.body()?.message)
                )
            } else {
                _forgotPasswordLiveData.postValue(
                    Resource.Error(getErrorMessage(forgetPasswordResponse))
                )
            }
        }
    }

    fun otpVerification(otp: String, email: String) {
        viewModelScope.launch {
            _otpVerificationLiveData.postValue(Resource.Loading())
            val otpVerificationRequestBody = OtpVerificationRequestBody(otp, email)
            val otpVerificationResponse = authRepository.otpVerification(otpVerificationRequestBody)

            if (otpVerificationResponse.isSuccessful) {
                _otpVerificationLiveData.postValue(
                    Resource.Success(data = Unit, message = otpVerificationResponse.body()?.message)
                )
            } else {
                _otpVerificationLiveData.postValue(Resource.Error())
            }
        }
    }

    fun resetPassword(email: String, password: String, confirmPassword: String, otp: String) {
        viewModelScope.launch {
            _resetPasswordLiveData.postValue(Resource.Loading())
            val resetPasswordRequestBody =
                ResetPasswordRequestBody(email, password, confirmPassword, otp)
            val resetPasswordResponse = authRepository.resetPassword(resetPasswordRequestBody)

            if (resetPasswordResponse.isSuccessful) {
                _resetPasswordLiveData.postValue(
                    Resource.Success(data = Unit, message = resetPasswordResponse.body()?.message)
                )
            } else {
                _resetPasswordLiveData.postValue(Resource.Error())
            }
        }
    }
}
