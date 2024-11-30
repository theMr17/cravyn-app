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
import com.cravyn.app.databinding.FragmentCartBinding
import com.cravyn.app.features.cart.adapters.CartRecyclerViewAdapter
import com.cravyn.app.features.cart.listeners.UpdateCartItemStatusListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), UpdateCartItemStatusListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        cartViewModel.getCart()

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
                            cartSummaryHeaderText.isVisible = false
                            cartSummaryContainer.isVisible = false
                            placeOrderButton.isVisible = false
                        }
                    } else {
                        binding.apply {
                            emptyCartText.isVisible = false
                            cartSummaryHeaderText.isVisible = true
                            cartSummaryContainer.isVisible = true
                            placeOrderButton.isVisible = true
                        }

                        it.data?.let { data ->
                            binding.apply {
                                totalPriceText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.totalPrice.toString()
                                )
                                totalDiscountText.text = requireContext().getString(
                                    R.string.formatted_discount_text,
                                    data.totalDiscount.toString()
                                )
                                deliveryChargeText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.deliveryCharge.toString()
                                )
                                platformChargeText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.platformCharge.toString()
                                )
                                finalPriceText.text = requireContext().getString(
                                    R.string.formatted_price_text,
                                    data.finalPrice.toString()
                                )
                            }
                        }
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
