package com.cravyn.app.features.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.features.search.SearchActivity
import com.cravyn.app.features.splash.SplashFragment

/** Tag for identifying the [ProfileFragment] in transactions. */
private const val TAG_PROFILE_FRAGMENT = "PROFILE_FRAGMENT"

class ProfileActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [SearchActivity]. */
        fun createProfileActivity(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (getSplashFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.profile_fragment_placeholder,
                ProfileFragment(),
                TAG_PROFILE_FRAGMENT
            ).commitNow()
        }
    }

    private fun getSplashFragment(): SplashFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.profile_fragment_placeholder
        ) as SplashFragment?
    }
}
