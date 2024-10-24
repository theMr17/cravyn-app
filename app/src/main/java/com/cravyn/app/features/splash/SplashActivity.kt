package com.cravyn.app.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [SplashFragment] in transactions. */
private const val TAG_SPLASH_FRAGMENT = "SPLASH_FRAGMENT"

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (getSplashFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.splash_fragment_placeholder,
                SplashFragment(),
                TAG_SPLASH_FRAGMENT
            ).commitNow()
        }
    }

    private fun getSplashFragment(): SplashFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.splash_fragment_placeholder
        ) as SplashFragment?
    }
}
