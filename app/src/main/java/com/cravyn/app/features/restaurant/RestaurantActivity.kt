package com.cravyn.app.features.restaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [RestaurantFragment] in transactions. */
private const val TAG_RESTAURANT_FRAGMENT = "RESTAURANT_FRAGMENT"

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [RestaurantActivity]. */
        fun createRestaurantActivity(context: Context): Intent {
            return Intent(context, RestaurantActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        if (getHomeFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.restaurant_fragment_placeholder,
                RestaurantFragment(),
                TAG_RESTAURANT_FRAGMENT
            ).commitNow()
        }
    }

    private fun getHomeFragment(): RestaurantFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.restaurant_fragment_placeholder
        ) as RestaurantFragment?
    }
}
