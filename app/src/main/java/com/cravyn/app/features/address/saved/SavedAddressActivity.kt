package com.cravyn.app.features.address.saved

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivitySavedAddressBinding

/** Tag for identifying the [SavedAddressFragment] in transactions. */
private const val TAG_SAVED_ADDRESS_FRAGMENT = "SAVED_ADDRESS_FRAGMENT"

class SavedAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedAddressBinding

    companion object {
        /** Returns a new [Intent] to route to [SavedAddressActivity]. */
        fun createSavedAddressActivity(context: Context): Intent {
            return Intent(context, SavedAddressActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (getSavedAddressFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.saved_address_fragment_placeholder,
                SavedAddressFragment(),
                TAG_SAVED_ADDRESS_FRAGMENT
            ).commitNow()
        }
    }

    private fun getSavedAddressFragment(): SavedAddressFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.saved_address_fragment_placeholder
        ) as SavedAddressFragment?
    }
}
