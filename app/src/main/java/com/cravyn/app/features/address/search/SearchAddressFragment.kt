package com.cravyn.app.features.address.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentSearchAddressBinding
import com.cravyn.app.features.address.AddressViewModel
import com.cravyn.app.features.address.adapters.SearchedAddressRecyclerViewAdapter
import com.cravyn.app.features.address.listeners.SaveAddressItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressFragment : Fragment(), SaveAddressItemClickListener {
    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!

    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)

        binding.searchAddressTextInputLayout.editText?.addTextChangedListener {
            addressViewModel.searchAddresses(binding.searchAddressTextInputLayout.editText?.text.toString())
        }

        addressViewModel.searchedAddressListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding.searchedAddressLoadingBar.isVisible = false
                }

                is Resource.Loading -> {
                    binding.searchedAddressLoadingBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.searchedAddressLoadingBar.isVisible = false
                    binding.searchAddressRecyclerView.adapter = it.data?.let { searchedAddresses ->
                        SearchedAddressRecyclerViewAdapter(
                            searchedAddresses,
                            this as SaveAddressItemClickListener
                        )
                    }
                }
            }
        }

        addressViewModel.saveAddressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    requireActivity().finish()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveButtonClicked(latitude: Double, longitude: Double, displayAddress: String) {
        addressViewModel.saveAddress(latitude, longitude, displayAddress)
    }
}
