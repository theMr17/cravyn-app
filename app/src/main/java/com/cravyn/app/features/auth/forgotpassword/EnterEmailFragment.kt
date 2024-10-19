package com.cravyn.app.features.auth.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.databinding.FragmentEnterEmailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterEmailFragment : Fragment() {
    private var _binding: FragmentEnterEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterEmailBinding.inflate(inflater, container, false)

        binding.sendOtpButton.setOnClickListener {
            (activity as ForgotPasswordPageChanger).changePage(ForgotPasswordPages.OTP_VERIFICATION.value)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
