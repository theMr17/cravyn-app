package com.cravyn.app.features.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [LoginFragment] in transactions. */
private const val TAG_LOGIN_FRAGMENT = "LOGIN_FRAGMENT"

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [LoginActivity]. */
        fun createLoginActivity(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (getLoginFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.login_fragment_placeholder,
                LoginFragment(),
                TAG_LOGIN_FRAGMENT
            ).commitNow()
        }

        onBackPressedDispatcher.addCallback {
            moveTaskToBack(true)
        }
    }

    private fun getLoginFragment(): LoginFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.login_fragment_placeholder
        ) as LoginFragment?
    }
}
