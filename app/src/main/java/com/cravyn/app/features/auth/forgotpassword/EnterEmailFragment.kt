package com.cravyn.app.features.auth.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentEnterEmailBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.util.LoadingBarUtil.showButtonLoadingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterEmailFragment : Fragment() {
    private var _binding: FragmentEnterEmailBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterEmailBinding.inflate(inflater, container, false)

        binding.sendOtpButton.setOnClickListener {
            val email = binding.emailTextInputLayout.editText?.text.toString()

            bundle = Bundle().apply {
                putString(BUNDLE_KEY_EMAIL, email)
            }

            authViewModel.forgotPassword(email)
        }

        authViewModel.forgotPasswordLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.sendOtpButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showButtonLoadingBar(
                        isLoading = true,
                        binding.sendOtpButton,
                        binding.loadingBar
                    )
                }

                is Resource.Success -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.sendOtpButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()

                    (activity as ForgotPasswordPageChanger).changePage(
                        ForgotPasswordPages.OTP_VERIFICATION.value,
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
