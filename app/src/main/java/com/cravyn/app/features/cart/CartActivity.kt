package com.cravyn.app.features.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [CartFragment] in transactions. */
private const val TAG_CART_FRAGMENT = "CART_FRAGMENT"

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        if (getCartFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.cart_fragment_placeholder,
                CartFragment(),
                TAG_CART_FRAGMENT
            ).commitNow()
        }
    }

    private fun getCartFragment(): CartFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.cart_fragment_placeholder
        ) as CartFragment?
    }
}
