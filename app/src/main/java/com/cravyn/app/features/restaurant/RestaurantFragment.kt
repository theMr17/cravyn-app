package com.cravyn.app.features.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.databinding.FragmentRestaurantBinding
import com.cravyn.app.features.home.models.FoodItem
import com.cravyn.app.features.restaurant.adapters.RestaurantMenuRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)

        val recyclerView = binding.restaurantMenuRecyclerView
        recyclerView.adapter = RestaurantMenuRecyclerViewAdapter(List(5) { FoodItem(1, "") })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
