package com.cravyn.app.features.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentRestaurantBinding
import com.cravyn.app.features.restaurant.adapters.RestaurantMenuRecyclerViewAdapter
import com.cravyn.app.features.restaurant.models.Restaurant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment(private val restaurant: Restaurant?) : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    private val restaurantViewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)

        restaurant?.let {
            restaurantViewModel.getRestaurantMenu(restaurant.restaurantId)

            binding.restaurantNameText.text = restaurant.name
            binding.deliveryEstimationText.text =
                "${restaurant.minTime}-${restaurant.maxTime} min • ${restaurant.distance.formatted} km"
            binding.ratingText.text = restaurant.rating.formatted
            binding.ratingCountText.text = "${restaurant.ratingCount} ratings"
        }

        restaurantViewModel.restaurantMenuLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    // binding.recommendedRestaurantsLoadingBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    // binding.recommendedRestaurantsLoadingBar.isVisible = true
                }

                is Resource.Success -> {
                    // binding.recommendedRestaurantsLoadingBar.isVisible = false
                    binding.restaurantMenuRecyclerView.adapter =
                        RestaurantMenuRecyclerViewAdapter(
                            it.data?.catalog ?: emptyList()
                        )
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
}
