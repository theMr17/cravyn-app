package com.cravyn.app.features.auth.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentNewPasswordBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.util.LoadingBarUtil.showButtonLoadingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : Fragment() {
    private var _binding: FragmentNewPasswordBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var email: String
    private lateinit var otp: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewPasswordBinding.inflate(inflater, container, false)

        arguments?.let {
            email = it.getString(BUNDLE_KEY_EMAIL, "")
            otp = it.getString(BUNDLE_KEY_OTP, "")
        }

        binding.updatePasswordButton.setOnClickListener {
            val password = binding.newPasswordTextInputLayout.editText?.text.toString()
            val confirmPassword =
                binding.confirmNewPasswordTextInputLayout.editText?.text.toString()

            authViewModel.resetPassword(email, password, confirmPassword, otp)
        }

        authViewModel.resetPasswordLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.updatePasswordButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showButtonLoadingBar(
                        isLoading = true,
                        binding.updatePasswordButton,
                        binding.loadingBar
                    )
                }

                is Resource.Success -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.updatePasswordButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()

                    requireActivity().finish()
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
