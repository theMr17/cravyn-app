package com.cravyn.app.features.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cravyn.app.databinding.FragmentSignUpBinding
import com.cravyn.app.features.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

private var _binding: FragmentSignUpBinding? = null
private val binding get() = _binding!!
@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.materialToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

