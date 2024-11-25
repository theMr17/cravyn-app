package com.cravyn.app.features.address.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivitySearchAddressBinding

/** Tag for identifying the [SearchAddressFragment] in transactions. */
private const val TAG_SEARCH_ADDRESS_FRAGMENT = "SEARCH_ADDRESS_FRAGMENT"

class SearchAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAddressBinding

    companion object {
        /** Returns a new [Intent] to route to [SearchAddressActivity]. */
        fun createSearchAddressActivity(context: Context): Intent {
            return Intent(context, SearchAddressActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (getSearchAddressFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.search_address_fragment_placeholder,
                SearchAddressFragment(),
                TAG_SEARCH_ADDRESS_FRAGMENT
            ).commitNow()
        }
    }

    private fun getSearchAddressFragment(): SearchAddressFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.search_address_fragment_placeholder
        ) as SearchAddressFragment?
    }
}
