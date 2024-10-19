package com.cravyn.app.features.auth.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgotPasswordViewModel : ViewModel() {
    private val _currentProgress: MutableLiveData<Int> = MutableLiveData(-1)
    val currentProgress: LiveData<Int> get() = _currentProgress

    fun updateCurrentProgress(progress: Int) {
        _currentProgress.postValue(progress)
    }
}
