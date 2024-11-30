package com.cravyn.app.features.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivityCartBinding
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [CartFragment] in transactions. */
private const val TAG_CART_FRAGMENT = "CART_FRAGMENT"

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    companion object {
        /** Returns a new [Intent] to route to [CartActivity]. */
        fun createCartActivity(context: Context): Intent {
            return Intent(context, CartActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

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
