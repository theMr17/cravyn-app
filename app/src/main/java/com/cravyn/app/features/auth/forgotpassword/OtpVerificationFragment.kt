package com.cravyn.app.features.auth.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.databinding.FragmentOtpVerificationBinding

class OtpVerificationFragment : Fragment() {
    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)

        binding.otpEditText.getText()
        binding.submitOtpButton.setOnClickListener {
            (activity as ForgotPasswordPageChanger).changePage(ForgotPasswordPages.NEW_PASSWORD.value)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
