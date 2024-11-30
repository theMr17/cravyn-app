package com.cravyn.app.features.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.R
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentRestaurantBinding
import com.cravyn.app.features.cart.CartViewModel
import com.cravyn.app.features.cart.listener.AddItemToCartItemClickListener
import com.cravyn.app.features.restaurant.adapters.RestaurantMenuRecyclerViewAdapter
import com.cravyn.app.features.restaurant.models.Restaurant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
RestaurantFragment : Fragment(), AddItemToCartItemClickListener {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    private val restaurantViewModel: RestaurantViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)

        val restaurant = arguments?.getSerializable(RESTAURANT_TAG) as? Restaurant

        restaurant?.let {
            restaurantViewModel.getRestaurantMenu(restaurant.restaurantId)

            binding.apply {
                restaurantNameText.text = restaurant.name
                deliveryEstimationText.text =
                    getString(
                        R.string.restaurant_delivery_estimation_text,
                        restaurant.minTime,
                        restaurant.maxTime,
                        restaurant.distance.formatted
                    )
                ratingText.text = restaurant.rating.formatted
                ratingCountText.text =
                    getString(
                        R.string.restaurant_rating_count_text,
                        restaurant.ratingCount
                    )
            }
        }

        restaurantViewModel.restaurantMenuLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    binding.restaurantMenuRecyclerView.adapter =
                        RestaurantMenuRecyclerViewAdapter(
                            it.data?.catalog ?: emptyList(),
                            this as AddItemToCartItemClickListener
                        )
                }
            }
        }

        cartViewModel.addItemToCartLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addItemToCartClicked(itemId: String) {
        cartViewModel.addItemToCart(itemId)
    }
}
