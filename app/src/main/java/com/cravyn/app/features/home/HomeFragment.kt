package com.cravyn.app.features.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentHomeBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.auth.login.LoginActivity.Companion.createLoginActivity
import com.cravyn.app.util.LoadingBarUtil.showButtonLoadingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            authViewModel.logout()
        }

        authViewModel.logoutLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.logoutButton,
                        binding.loadingBar
                    )
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showButtonLoadingBar(
                        isLoading = true,
                        binding.logoutButton,
                        binding.loadingBar
                    )
                }

                is Resource.Success -> {
                    showButtonLoadingBar(
                        isLoading = false,
                        binding.logoutButton,
                        binding.loadingBar
                    )
                    startActivity(
                        createLoginActivity(requireContext()).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
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
