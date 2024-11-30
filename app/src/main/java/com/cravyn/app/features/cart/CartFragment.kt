package com.cravyn.app.features.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.R
import com.cravyn.app.data.api.Resource
import com.cravyn.app.data.api.toDisplayableNumber
import com.cravyn.app.databinding.FragmentCartBinding
import com.cravyn.app.features.address.AddressViewModel
import com.cravyn.app.features.address.saved.SavedAddressActivity.Companion.createSavedAddressActivity
import com.cravyn.app.features.cart.adapters.CartRecyclerViewAdapter
import com.cravyn.app.features.cart.listeners.UpdateCartItemStatusListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), UpdateCartItemStatusListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.deliveryAddressHeaderText.setOnClickListener {
            startActivity(createSavedAddressActivity(requireContext()))
        }

        binding.placeOrderButton.setOnClickListener {
            addressViewModel.defaultAddressLiveData.value?.data?.addresses?.get(0)?.addressId?.let { addressId ->
                cartViewModel.placeOrder(
                    binding.specificationsTextInputLayout.editText?.text.toString(),
                    addressId
                )
            } ?: run {
                Toast.makeText(requireContext(), "Please select a delivery address.", Toast.LENGTH_LONG).show()
            }
        }

        cartViewModel.getCart()

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        cartViewModel.cartLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Success -> {
                    binding.cartItemsRecyclerView.adapter =
                        CartRecyclerViewAdapter(
                            it.data?.cart ?: emptyList(),
                            this as UpdateCartItemStatusListener
                        )

                    if (it.data?.cart.isNullOrEmpty()) {
                        binding.apply {
                            emptyCartText.isVisible = true
                            cartItemsContainer.isVisible = false
                        }
                    } else {
                        binding.apply {
                            emptyCartText.isVisible = false
                            cartItemsContainer.isVisible = true

                            it.data?.let { data ->
                                totalPriceText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.totalPrice.toDisplayableNumber().formatted
                                )
                                totalDiscountText.text = requireContext().getString(
                                    R.string.formatted_discount_text,
                                    data.totalDiscount.toDisplayableNumber().formatted
                                )
                                deliveryChargeText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.deliveryCharge.toDouble().toDisplayableNumber().formatted
                                )
                                platformChargeText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.platformCharge.toDouble().toDisplayableNumber().formatted
                                )
                                finalPriceText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.finalPrice.toDisplayableNumber().formatted
                                )
                            }
                        }
                    }
                }
            }
        }

        cartViewModel.placeOrderLiveData.observe(viewLifecycleOwner) {
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

        addressViewModel.defaultAddressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    binding.deliveryAddressText.text = it.data?.addresses?.get(0)?.displayAddress
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        addressViewModel.getDefaultAddress()
    }

    override fun onPlusButtonClicked(itemId: String) {
        cartViewModel.incrementItemCount(itemId)
    }

    override fun onMinusButtonClicked(itemId: String) {
        cartViewModel.decrementItemCount(itemId)
    }

    override fun onRemoveItemButtonClicked(itemId: String) {
        cartViewModel.deleteItemFromCart(itemId)
    }
}
