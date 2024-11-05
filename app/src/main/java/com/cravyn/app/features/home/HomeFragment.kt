package com.cravyn.app.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.databinding.FragmentHomeBinding
import com.cravyn.app.features.auth.AuthViewModel
import com.cravyn.app.features.adapter.GridAdapter
import com.cravyn.app.features.adapter.RecommandedRestaurantAdapter
import com.cravyn.app.features.auth.models.FoodItem
import com.cravyn.app.features.auth.models.RestaurantItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var foodItem : List<FoodItem>
    private lateinit var gridView: GridView
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantItem: List<RestaurantItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        gridView = binding.gridView
        foodItem = listOf(
            FoodItem(R.drawable.ic_kabab, "Kabab"),
            FoodItem(R.drawable.ic_noodles, "Noodles"),
            FoodItem(R.drawable.ic_burger, "Burger"),
            FoodItem(R.drawable.ic_sweets, "Sweets"),
            FoodItem(R.drawable.ic_salad, "Salad"),
            FoodItem(R.drawable.ic_pizza, "Pizza"),
            FoodItem(R.drawable.ic_biriyani, "Biriyani"),
            FoodItem(R.drawable.ic_south_indian, "South Indian"),
            FoodItem(R.drawable.ic_north_indian, "North Indian"),
            FoodItem(R.drawable.ic_roll, "Roll"),
            FoodItem(R.drawable.ic_momo, "Momo"),
            FoodItem(R.drawable.ic_icecream, "Ice Cream")
        )

        gridView.adapter = GridAdapter(requireContext(), foodItem)

        recyclerView = binding.recycleView

        restaurantItem= listOf(
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address"),
            RestaurantItem(R.drawable.restaurant_sample_image,"20% OFF","Upto ₹100", "Biriyani","4.4(10k+)",".15-20 mins","Restaurant address")
        )

        recyclerView.adapter = RecommandedRestaurantAdapter(restaurantItem)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
