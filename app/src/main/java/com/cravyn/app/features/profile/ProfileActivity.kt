package com.cravyn.app.features.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivityProfileBinding

/** Tag for identifying the [ProfileFragment] in transactions. */
private const val TAG_PROFILE_FRAGMENT = "PROFILE_FRAGMENT"

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    companion object {
        /** Returns a new [Intent] to route to [ProfileActivity]. */
        fun createProfileActivity(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (getProfileFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.profile_fragment_placeholder,
                ProfileFragment(),
                TAG_PROFILE_FRAGMENT
            ).commitNow()
        }
    }

    private fun getProfileFragment(): ProfileFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.profile_fragment_placeholder
        ) as ProfileFragment?
    }
}
