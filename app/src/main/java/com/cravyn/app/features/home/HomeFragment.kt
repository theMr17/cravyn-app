package com.cravyn.app.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.R
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentHomeBinding
import com.cravyn.app.features.home.adapters.RecommendedFoodGridViewAdapter
import com.cravyn.app.features.home.adapters.RecommendedRestaurantRecyclerViewAdapter
import com.cravyn.app.features.home.models.FoodItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val foodItem = listOf(
            FoodItem(R.drawable.ic_kebab, "Kebab"),
            FoodItem(R.drawable.ic_noodles, "Noodles"),
            FoodItem(R.drawable.ic_burger, "Burger"),
            FoodItem(R.drawable.ic_sweets, "Sweets"),
            FoodItem(R.drawable.ic_salad, "Salad"),
            FoodItem(R.drawable.ic_pizza, "Pizza"),
            FoodItem(R.drawable.ic_biryani, "Biryani"),
            FoodItem(R.drawable.ic_south_indian, "South Indian"),
            FoodItem(R.drawable.ic_north_indian, "North Indian"),
            FoodItem(R.drawable.ic_roll, "Roll"),
            FoodItem(R.drawable.ic_momo, "Momo"),
            FoodItem(R.drawable.ic_icecream, "Ice Cream")
        )

        val gridView = binding.recommendedFoodGridView
        gridView.adapter = RecommendedFoodGridViewAdapter(requireContext(), foodItem)

        homeViewModel.getRecommendedRestaurants(22.6865, 88.4694)

        homeViewModel.recommendedRestaurantsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    binding.recommendedRestaurantRecyclerView.adapter =
                        RecommendedRestaurantRecyclerViewAdapter(it.data ?: emptyList())
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
