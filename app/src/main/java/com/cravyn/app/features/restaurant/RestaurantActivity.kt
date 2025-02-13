package com.cravyn.app.features.restaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.features.restaurant.models.Restaurant
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [RestaurantFragment] in transactions. */
private const val TAG_RESTAURANT_FRAGMENT = "RESTAURANT_FRAGMENT"

/** Tag for the restaurantId extra. */
const val RESTAURANT_TAG = "restaurant"

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
    companion object {
        /** Returns a new [Intent] to route to [RestaurantActivity]. */
        fun createRestaurantActivity(context: Context, restaurant: Restaurant): Intent {
            return Intent(context, RestaurantActivity::class.java).apply {
                putExtra(RESTAURANT_TAG, restaurant)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val restaurant = intent.getSerializableExtra(RESTAURANT_TAG) as? Restaurant

        if (getRestaurantFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.restaurant_fragment_placeholder,
                RestaurantFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(RESTAURANT_TAG, restaurant)
                    }
                },
                TAG_RESTAURANT_FRAGMENT
            ).commitNow()
        }
    }

    private fun getRestaurantFragment(): RestaurantFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.restaurant_fragment_placeholder
        ) as RestaurantFragment?
    }
}
