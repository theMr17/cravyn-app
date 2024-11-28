package com.cravyn.app.features.address.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentSavedAddressBinding
import com.cravyn.app.features.address.AddressViewModel
import com.cravyn.app.features.address.adapters.SavedAddressRecyclerViewAdapter
import com.cravyn.app.features.address.listeners.RemoveAddressItemClickListener
import com.cravyn.app.features.address.listeners.SaveAddressAsDefaultItemClickListener
import com.cravyn.app.features.address.search.SearchAddressActivity.Companion.createSearchAddressActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedAddressFragment : Fragment(), RemoveAddressItemClickListener,
    SaveAddressAsDefaultItemClickListener {
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
                    binding.savedAddressLoadingBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    binding.savedAddressLoadingBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.savedAddressLoadingBar.isVisible = false
                    binding.savedAddressesRecyclerView.adapter = it.data?.let { savedAddresses ->
                        SavedAddressRecyclerViewAdapter(
                            savedAddresses.addresses,
                            this as RemoveAddressItemClickListener,
                            this as SaveAddressAsDefaultItemClickListener
                        )
                    }
                }
            }
        }

        addressViewModel.removeAddressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    addressViewModel.getSavedAddresses()
                }
            }
        }

        addressViewModel.setDefaultAddressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
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

    override fun onResume() {
        super.onResume()
        addressViewModel.getSavedAddresses()
    }

    override fun onRemoveButtonClicked(addressId: String) {
        addressViewModel.removeAddress(addressId)
    }

    override fun onSaveAsDefaultButtonClicked(addressId: String) {
        addressViewModel.setDefaultAddress(addressId)
    }
}
