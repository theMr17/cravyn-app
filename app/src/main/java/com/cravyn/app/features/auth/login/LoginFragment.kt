package com.cravyn.app.features.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.databinding.FragmentLoginBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.auth.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

private var _binding: FragmentLoginBinding? = null
private val binding get() = _binding!!


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.joinTheFeast.setOnClickListener {
            startActivity(SignUpActivity.createSignUpActivity(requireContext()))
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
