package com.cravyn.app.features.address.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentSavedAddressBinding
import com.cravyn.app.features.address.AddressViewModel
import com.cravyn.app.features.address.search.SearchAddressActivity.Companion.createSearchAddressActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedAddressFragment : Fragment() {
    private var _binding: FragmentSavedAddressBinding? = null
    private val binding get() = _binding!!

    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedAddressBinding.inflate(inflater, container, false)

        binding.addNewAddressButton.setOnClickListener {
            startActivity(createSearchAddressActivity(requireContext()))
        }

        addressViewModel.getSavedAddresses()
        addressViewModel.savedAddressesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {

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
