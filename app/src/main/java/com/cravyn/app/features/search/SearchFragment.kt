package com.cravyn.app.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentSearchBinding
import com.cravyn.app.features.search.adapters.SearchedFoodsRecyclerViewAdapter
import com.cravyn.app.features.search.adapters.SearchedRestaurantsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel:SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchTextInputLayout.editText?.requestFocus()

        val searchQuery = arguments?.getString("food_title")
        searchQuery?.let {
            binding.searchTextInputLayout.editText?.setText(it)
            searchViewModel.getSearchedFoodAndRestaurants(it)
        }
        binding.searchTextInputLayout.editText?.addTextChangedListener {
            searchViewModel.getSearchedFoodAndRestaurants(
                binding.searchTextInputLayout.editText?.text.toString()
            )
        }

        searchViewModel.searchedFoodAndRestaurantLivedata.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    binding.searchPageLoadingBar.isVisible = true
                }
                is Resource.Error -> {
                    binding.searchPageLoadingBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    binding.searchPageLoadingBar.isVisible = false
                    binding.searchedFoodsRecyclerView.adapter =
                        SearchedFoodsRecyclerViewAdapter(
                            it.data?.foodItems?: emptyList()
                        )
                    binding.searchedRestaurantsRecyclerView.adapter =
                        SearchedRestaurantsRecyclerViewAdapter(
                            it.data?.restaurants?: emptyList()
                        )
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
