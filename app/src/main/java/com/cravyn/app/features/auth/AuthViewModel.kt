package com.cravyn.app.features.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.ForgetPasswordRequestBody
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

/** Tag for identifying the [AuthViewModel] in transactions. */
private const val TAG_AUTH_VIEW_MODEL = "AUTH_VIEW_MODEL"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _loginLiveData: MutableLiveData<Resource<User>> = MutableLiveData()
    val loginLiveData: LiveData<Resource<User>> get() = _loginLiveData

    private val _registerLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val registerLiveData: LiveData<Resource<Unit>> get() = _registerLiveData

    private val _forgetPasswordLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val forgetPasswordLiveData: LiveData<Resource<Unit>> get() = _forgetPasswordLiveData

    private val _otpVerificationLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val otpVerificationLiveData: LiveData<Resource<Unit>> get() = _otpVerificationLiveData

    private val _resetPasswordLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val resetPasswordLiveData: LiveData<Resource<Unit>> get() = _resetPasswordLiveData

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

    private suspend fun saveUserToDatabase(user: User) {
        authRepository.saveUserToDatabase(user).collectLatest { result ->
            when (result) {
                is Resource.Loading -> Log.d(TAG_AUTH_VIEW_MODEL, "Adding user to database.")
                is Resource.Success -> Log.d(TAG_AUTH_VIEW_MODEL, "User added successfully.")
                is Resource.Error -> Log.e(TAG_AUTH_VIEW_MODEL, "Failed to add user to database.")
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
                    Resource.Success(data = Unit)
                )
            } else {
                _registerLiveData.postValue(
                    Resource.Error(getErrorMessage(registerResponse))
                )
            }
        }
    }

    fun forgetPassword(email: String) {
        viewModelScope.launch {
            _forgetPasswordLiveData.postValue(Resource.Loading())
            val forgetPasswordRequestBody = ForgetPasswordRequestBody(email)
            val forgetPasswordResponse = authRepository.forgetPassword(forgetPasswordRequestBody)

            if (forgetPasswordResponse.isSuccessful) {
                _forgetPasswordLiveData.postValue(
                    Resource.Success(data = Unit)
                )
            } else {
                _forgetPasswordLiveData.postValue(
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
                    Resource.Success(
                        data = Unit
                    )
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
                    Resource.Success(
                        data = Unit
                    )
                )
            } else {
                _resetPasswordLiveData.postValue(Resource.Error())
            }
        }
    }


}
