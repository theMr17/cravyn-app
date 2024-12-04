package com.cravyn.app.features.profile

import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.cravyn.app.R
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentProfileBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.auth.login.LoginActivity.Companion.createLoginActivity
import com.cravyn.app.util.toHttpsUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.updateProfileButton.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Sorry, this feature is not available yet.",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.logoutButton.setOnClickListener {
            authViewModel.logout()
        }

        profileViewModel.getProfile()

        authViewModel.logoutLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    startActivity(createLoginActivity(requireContext()).apply {
                        flags = FLAG_ACTIVITY_CLEAR_TOP
                    })
                }
            }
        }

        profileViewModel.profileLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    binding.apply {
                        profileName.text = it.data?.customer?.name
                        Glide.with(requireContext())
                            .load(it.data?.customer?.profileImageUrl.toHttpsUrl())
                            .placeholder(R.drawable.ic_account_solid)
                            .error(R.drawable.ic_account_solid)
                            .into(profileImage)
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
