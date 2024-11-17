package com.cravyn.app.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.R
import com.cravyn.app.databinding.FragmentHomeBinding
import com.cravyn.app.features.home.adapters.RecommendedFoodGridViewAdapter
import com.cravyn.app.features.home.adapters.RecommendedRestaurantRecyclerViewAdapter
import com.cravyn.app.features.home.models.FoodItem
import com.cravyn.app.features.home.models.RestaurantItem
import com.cravyn.app.features.search.SearchActivity.Companion.createSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.searchTextInputLayout.editText?.setOnClickListener {
            startActivity(createSearchActivity(requireContext()))
        }

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

        val restaurantItem = listOf(
            RestaurantItem(
                R.drawable.restaurant_sample_image,
                "20% OFF",
                "Up to ₹100",
                "Biryani",
                "4.4 (10K+)",
                "15-20 mins",
                "Salt Lake | 2.5 km "
            ),
            RestaurantItem(
                R.drawable.restaurant_sample_image,
                "20% OFF",
                "Up to ₹100",
                "Biryani",
                "4.4 (10K+)",
                "15-20 mins",
                "Salt Lake | 2.5 km "
            ),
            RestaurantItem(
                R.drawable.restaurant_sample_image,
                "20% OFF",
                "Up to ₹100",
                "Biryani",
                "4.4 (10K+)",
                "15-20 mins",
                "Salt Lake | 2.5 km "
            ),
            RestaurantItem(
                R.drawable.restaurant_sample_image,
                "20% OFF",
                "Up to ₹100",
                "Biryani",
                "4.4 (10K+)",
                "15-20 mins",
                "Salt Lake | 2.5 km "
            ),
            RestaurantItem(
                R.drawable.restaurant_sample_image,
                "20% OFF",
                "Up to ₹100",
                "Biryani",
                "4.4 (10K+)",
                "15-20 mins",
                "Salt Lake | 2.5 km "
            ),
            RestaurantItem(
                R.drawable.restaurant_sample_image,
                "20% OFF",
                "Up to ₹100",
                "Biryani",
                "4.4 (10K+)",
                "15-20 mins",
                "Salt Lake | 2.5 km "
            ),
        )

        val recyclerView = binding.recommendedRestaurantRecyclerView
        recyclerView.adapter = RecommendedRestaurantRecyclerViewAdapter(restaurantItem)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
