package com.cravyn.app.features.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentLoginBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.auth.forgotpassword.ForgotPasswordActivity.Companion.createForgotPasswordActivity
import com.cravyn.app.features.auth.signup.SignUpActivity.Companion.createSignUpActivity
import com.cravyn.app.util.LoadingBarUtil.showButtonLoadingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.forgotPasswordText.setOnClickListener {
            startActivity(createForgotPasswordActivity(requireContext()))
        }

        binding.joinTheFeast.setOnClickListener {
            startActivity(createSignUpActivity(requireContext()))
        }

        binding.letsEatButton.setOnClickListener {
            val email = binding.emailTextInputLayout.editText?.text.toString()
            val password = binding.passwordTextInputLayout.editText?.text.toString()

            authViewModel.login(email, password)
        }

        authViewModel.loginLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.letsEatButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showButtonLoadingBar(
                        isLoading = true,
                        binding.letsEatButton,
                        binding.loadingBar
                    )
                }

                is Resource.Success -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.letsEatButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
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
