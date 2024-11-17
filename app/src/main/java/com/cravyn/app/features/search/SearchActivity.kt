package com.cravyn.app.features.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [SearchFragment] in transactions. */
private const val TAG_SEARCH_FRAGMENT = "SEARCH_FRAGMENT"

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [SearchActivity]. */
        fun createSearchActivity(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (getSearchFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.search_fragment_placeholder,
                SearchFragment(),
                TAG_SEARCH_FRAGMENT
            ).commitNow()
        }
    }

    private fun getSearchFragment(): SearchFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.search_fragment_placeholder
        ) as SearchFragment?
    }
}
