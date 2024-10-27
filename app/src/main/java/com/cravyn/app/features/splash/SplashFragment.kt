package com.cravyn.app.features.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentSplashBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.auth.login.LoginActivity.Companion.createLoginActivity
import com.cravyn.app.features.home.HomeActivity.Companion.createHomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        authViewModel.isUserLoggedIn()

        authViewModel.isUserLoggedInLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    if (it.data == true) {
                        startActivity(
                            createHomeActivity(requireContext()).apply {
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        )
                    } else {
                        startActivity(
                            createLoginActivity(requireContext()).apply {
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        )
                    }
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
