package com.cravyn.app.features.auth.forgotpassword

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for managing the state of the Forgot Password process.
 *
 * This ViewModel holds the progress of the forgot password workflow
 * and allows data transfer between fragments.
 */
class ForgotPasswordViewModel : ViewModel() {
    private val _currentProgress: MutableLiveData<Int> = MutableLiveData(-1)
    val currentProgress: LiveData<Int> get() = _currentProgress

    // Bundle used to transfer data from one fragment to another.
    private var _bundle: Bundle? = null
    var bundle: Bundle?
        get() = _bundle
        set(value) { _bundle = value }

    /**
     * Updates the current progress with a new value.
     *
     * @param progress The new progress value, should be between 0 and 2.
     */
    fun updateCurrentProgress(progress: Int) {
        _currentProgress.postValue(progress)
    }
}
