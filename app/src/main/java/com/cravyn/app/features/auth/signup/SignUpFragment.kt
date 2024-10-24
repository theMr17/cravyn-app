package com.cravyn.app.features.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentSignUpBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.auth.login.LoginActivity
import com.cravyn.app.util.LoadingBarUtil.showButtonLoadingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.grabASeatButton.setOnClickListener {
            val name = binding.nameTextInputLayout.editText?.text.toString()
            val email = binding.emailTextInputLayout.editText?.text.toString()
            val phoneNumber = binding.phoneTextInputLayout.editText?.text.toString()
            val dateOfBirth = binding.dobTextInputLayout.editText?.text.toString()
            val password = binding.passwordTextInputLayout.editText?.text.toString()
            val confirmPassword = binding.confirmPasswordTextInputLayout.editText?.text.toString()

            authViewModel.register(name, email, phoneNumber, dateOfBirth, password, confirmPassword)
        }

        authViewModel.registerLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.grabASeatButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                }

                is Resource.Loading -> {
                    showButtonLoadingBar(
                        isLoading = true,
                        binding.grabASeatButton,
                        binding.loadingBar
                    )
                }

                is Resource.Success -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.grabASeatButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                        .show()
                    startActivity(LoginActivity.createLoginActivity(requireContext()))
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
