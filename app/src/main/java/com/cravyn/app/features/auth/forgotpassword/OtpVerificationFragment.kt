package com.cravyn.app.features.auth.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentOtpVerificationBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.util.LoadingBarUtil.showButtonLoadingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerificationFragment : Fragment() {
    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var email: String
    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)

        arguments?.let {
            email = it.getString(BUNDLE_KEY_EMAIL, "")
        }

        binding.submitOtpButton.setOnClickListener {
            val otp = binding.otpEditText.getText()

            bundle = Bundle().apply {
                putString(BUNDLE_KEY_EMAIL, email)
                putString(BUNDLE_KEY_OTP, otp)
            }

            authViewModel.otpVerification(otp, email)
        }

        authViewModel.otpVerificationLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.submitOtpButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showButtonLoadingBar(
                        isLoading = true,
                        binding.submitOtpButton,
                        binding.loadingBar
                    )
                }

                is Resource.Success -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.submitOtpButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()

                    (activity as ForgotPasswordPageChanger).changePage(
                        ForgotPasswordPages.NEW_PASSWORD.value,
                        bundle
                    )
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
