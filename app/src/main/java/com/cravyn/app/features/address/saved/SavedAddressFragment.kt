package com.cravyn.app.features.address.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.databinding.FragmentSavedAddressBinding
import com.cravyn.app.features.address.search.SearchAddressActivity.Companion.createSearchAddressActivity

class SavedAddressFragment : Fragment() {
    private var _binding: FragmentSavedAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedAddressBinding.inflate(inflater, container, false)

        binding.addNewAddressButton.setOnClickListener {
            startActivity(createSearchAddressActivity(requireContext()))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
