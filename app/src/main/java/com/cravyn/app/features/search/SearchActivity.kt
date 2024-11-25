package com.cravyn.app.features.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.features.home.models.FoodItem
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [SearchFragment] in transactions. */
private const val TAG_SEARCH_FRAGMENT = "SEARCH_FRAGMENT"

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [SearchActivity]. */
        fun createSearchActivity(context: Context, foodItem: FoodItem): Intent {
            return Intent(context, SearchActivity::class.java).apply {
                putExtra("food_title", foodItem.title)
            }
        }

        fun createSearchActivity(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchQuery = intent.getStringExtra("food_title")

        if (getSearchFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.search_fragment_placeholder,
                SearchFragment().apply {
                    arguments = Bundle().apply {
                        putString("food_title", searchQuery)
                    }
                },
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
