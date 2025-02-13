package com.cravyn.app.features.auth.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [SignUpFragment] in transactions. */
private const val TAG_SIGN_UP_FRAGMENT = "SIGN_UP_FRAGMENT"

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [SignUpActivity]. */
        fun createSignUpActivity(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        if (getSignUpFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.signup_fragment_placeholder,
                SignUpFragment(),
                TAG_SIGN_UP_FRAGMENT
            ).commitNow()
        }
    }

    private fun getSignUpFragment(): SignUpFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.signup_fragment_placeholder
        ) as SignUpFragment?
    }
}
