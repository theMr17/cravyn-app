package com.cravyn.app.features.address.saved

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R

/** Tag for identifying the [SavedAddressFragment] in transactions. */
private const val TAG_SAVED_ADDRESS_FRAGMENT = "SAVED_ADDRESS_FRAGMENT"

class SavedAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_address)

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
