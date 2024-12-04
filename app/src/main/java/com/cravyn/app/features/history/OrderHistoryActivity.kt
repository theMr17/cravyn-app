package com.cravyn.app.features.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivityOrderHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [OrderHistoryFragment] in transactions. */
private const val TAG_ORDER_HISTORY_FRAGMENT = "ORDER_HISTORY_FRAGMENT"

@AndroidEntryPoint
class OrderHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderHistoryBinding

    companion object {
        /** Returns a new [Intent] to route to [OrderHistoryActivity]. */
        fun createOrderHistoryActivity(context: Context): Intent {
            return Intent(context, OrderHistoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (getOrderHistoryFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.order_history_fragment_placeholder,
                OrderHistoryFragment(),
                TAG_ORDER_HISTORY_FRAGMENT
            ).commitNow()
        }
    }

    private fun getOrderHistoryFragment(): OrderHistoryFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.order_history_fragment_placeholder
        ) as OrderHistoryFragment?
    }
}
