package com.cravyn.app.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravyn.app.databinding.FragmentSearchBinding
import com.cravyn.app.features.home.models.FoodItem
import com.cravyn.app.features.search.adapters.SearchedFoodsRecyclerViewAdapter

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val searchedFoodsRecyclerView = binding.searchedFoodsRecyclerView
        searchedFoodsRecyclerView.adapter = SearchedFoodsRecyclerViewAdapter(List(2) { FoodItem(1, "") })

        return binding.root
    }
}
