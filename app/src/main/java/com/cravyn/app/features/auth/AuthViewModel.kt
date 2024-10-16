package com.cravyn.app.features.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.auth.models.LoginRequestBody
import com.cravyn.app.features.auth.models.RegisterRequestBody
import com.cravyn.app.features.auth.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun register(
        name: String,
        phoneNumber: String,
        emailAddress: String,
        dateOfBirth: String,
        password: String,
        confirmPassword: String ) {
        viewModelScope.launch {
            val registerRequestBody = RegisterRequestBody(name,phoneNumber,emailAddress,dateOfBirth,password,confirmPassword)
            val registerResponse = authRepository.register(registerRequestBody)

            if(registerResponse.isSuccessful){

            }
            else {

            }
        }

    }
    /**,
     * Attempts to log in the user with the provided [email] and [password].
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            val loginRequestBody = LoginRequestBody(email, password)
            val response = authRepository.login(loginRequestBody)

            if (response.isSuccessful) {
                response.body()?.data?.customer?.let { customer ->
                    val user = User(
                        id = customer.id,
                        dateOfBirth = customer.dateOfBirth,
                        emailAddress = customer.emailAddress,
                        name = customer.name,
                        phoneNumber = customer.phoneNumber,
                        profileImageUrl = customer.profileImageUrl,
                        accessToken = response.body()?.data?.accessToken,
                        refreshToken = response.body()?.data?.refreshToken
                    )
                    // Add user to the database and collect the result.
                    authRepository.addUserToDatabase(user).collectLatest { result ->
                        when (result) {
                            is Resource.Loading -> Log.d("Auth", "Adding user to database.")
                            is Resource.Success -> Log.d("Auth", "User added successfully.")
                            is Resource.Error -> Log.e("Auth", "Failed to add user to database.")
                        }
                    }
                } ?: Log.e("Auth", "Response body or customer data is null.")
            } else {
                Log.e("Auth", "Login failed with status code: ${response.code()}.")
            }
        }
    }
}
