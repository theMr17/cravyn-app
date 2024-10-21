package com.cravyn.app.features.auth.forgotpassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cravyn.app.R
import com.cravyn.app.databinding.ActivityForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity(), ForgotPasswordPageChanger {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels()

    companion object {
        /** Returns a new [Intent] to route to [ForgotPasswordActivity]. */
        fun createForgotPasswordActivity(context: Context): Intent {
            return Intent(context, ForgotPasswordActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        initializeFragment(forgotPasswordViewModel.currentProgress.value ?: -1)
    }

    private fun setupObservers() {
        forgotPasswordViewModel.currentProgress.observe(this) { progress ->
            updateProgressUi(progress)
        }
    }

    private fun updateProgressUi(progress: Int) {
        binding.forgotPasswordProgressBar.apply {
            this.progress = progress
            max = ForgotPasswordPages.entries.size
        }
        binding.currentStepCountText.text = getString(
            R.string.current_step_count, (progress + 1), ForgotPasswordPages.entries.size
        )
    }

    private fun initializeFragment(currentFragmentIndex: Int) {
        when (currentFragmentIndex) {
            -1 -> showFragment(EnterEmailFragment(), ForgotPasswordPages.ENTER_EMAIL.value)
            0 -> showFragment(
                getOrCreateFragment<EnterEmailFragment>(),
                ForgotPasswordPages.ENTER_EMAIL.value
            )

            1 -> showFragment(
                getOrCreateFragment<OtpVerificationFragment>(),
                ForgotPasswordPages.OTP_VERIFICATION.value
            )

            2 -> showFragment(
                getOrCreateFragment<NewPasswordFragment>(),
                ForgotPasswordPages.NEW_PASSWORD.value
            )
        }
    }

    private inline fun <reified T : Fragment> getOrCreateFragment(): T {
        return supportFragmentManager.findFragmentById(R.id.forgot_password_fragment_placeholder) as? T
            ?: T::class.java.getDeclaredConstructor().newInstance()
    }

    private fun showFragment(fragment: Fragment, progress: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.forgot_password_fragment_placeholder, fragment)
            .commitNow()
        forgotPasswordViewModel.updateCurrentProgress(progress)
    }

    override fun changePage(pageNumber: Int, bundle: Bundle) {
        val fragment = when (pageNumber) {
            ForgotPasswordPages.ENTER_EMAIL.value -> EnterEmailFragment()
            ForgotPasswordPages.OTP_VERIFICATION.value -> OtpVerificationFragment()
            ForgotPasswordPages.NEW_PASSWORD.value -> NewPasswordFragment()
            else -> throw IllegalArgumentException("Invalid page number")
        }
        fragment.arguments = bundle
        showFragment(fragment, pageNumber)
    }
}
