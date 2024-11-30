package com.cravyn.app.features.query

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivityQueryBinding
import dagger.hilt.android.AndroidEntryPoint

/** Tag for identifying the [QueryFragment] in transactions. */
private const val TAG_QUERY_FRAGMENT = "QUERY_FRAGMENT"

@AndroidEntryPoint
class QueryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQueryBinding

    companion object {
        /** Returns a new [Intent] to route to [QueryActivity]. */
        fun createQueryActivity(context: Context): Intent {
            return Intent(context, QueryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (getQueryFragment() == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.query_fragment_placeholder,
                QueryFragment(),
                TAG_QUERY_FRAGMENT
            ).commitNow()
        }
    }

    private fun getQueryFragment(): QueryFragment? {
        return supportFragmentManager.findFragmentById(
            R.id.query_fragment_placeholder
        ) as QueryFragment?
    }
}
