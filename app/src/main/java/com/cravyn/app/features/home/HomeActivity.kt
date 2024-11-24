package com.cravyn.app.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.features.home.listeners.RecommendedRestaurantItemClickListener
import com.cravyn.app.features.restaurant.RestaurantActivity.Companion.createRestaurantActivity
import com.cravyn.app.features.restaurant.models.Restaurant
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [HomeFragment] in transactions. */
private const val TAG_HOME_FRAGMENT = "HOME_FRAGMENT"

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RecommendedRestaurantItemClickListener {
    companion object {
        /** Returns a new [Intent] to route to [HomeActivity]. */
        fun createHomeActivity(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (getHomeFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.home_fragment_placeholder,
                HomeFragment(),
                TAG_HOME_FRAGMENT
            ).commitNow()
        }
    }

    private fun getHomeFragment(): HomeFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.home_fragment_placeholder
        ) as HomeFragment?
    }

    override fun onRecommendedRestaurantItemClicked(restaurant: Restaurant) {
        startActivity(createRestaurantActivity(this, restaurant))
    }
}
