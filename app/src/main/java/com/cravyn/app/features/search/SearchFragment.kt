package com.cravyn.app.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.data.api.toDisplayableNumber
import com.cravyn.app.databinding.FragmentSearchBinding
import com.cravyn.app.features.home.models.FoodItem
import com.cravyn.app.features.home.models.RecommendedRestaurantItem
import com.cravyn.app.features.search.adapters.SearchedFoodsRecyclerViewAdapter
import com.cravyn.app.features.search.adapters.SearchedRestaurantsRecyclerViewAdapter

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val searchedFoodsRecyclerView = binding.searchedFoodsRecyclerView
        searchedFoodsRecyclerView.adapter =
            SearchedFoodsRecyclerViewAdapter(List(2) { FoodItem(1, "") })

        val searchedRestaurantsRecyclerView = binding.searchedRestaurantsRecyclerView
        searchedRestaurantsRecyclerView.adapter = SearchedRestaurantsRecyclerViewAdapter(List(2) {
            RecommendedRestaurantItem(
                restaurantId = "",
                name = "Arsalan",
                city = "Salt Lake",
                distance = 12.5.toDisplayableNumber(),
                maxDiscountPercent = 20.0.toDisplayableNumber(0),
                maxDiscountCap = 20.0.toDisplayableNumber(0),
                minTime = 5,
                maxTime = 12,
                ratingCount = 320,
                rating = 4.5.toDisplayableNumber(1),
                restaurantImageUrl = "",
            )
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
