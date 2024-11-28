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
import com.cravyn.app.features.cart.CartViewModel
import com.cravyn.app.features.cart.listener.AddItemToCartItemClickListener
import com.cravyn.app.features.search.adapters.SearchedFoodsRecyclerViewAdapter
import com.cravyn.app.features.search.adapters.SearchedRestaurantsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), AddItemToCartItemClickListener{
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchTextInputLayout.editText?.requestFocus()

        val searchQuery = arguments?.getString(SEARCH_QUERY_TAG)
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
            when (it) {
                is Resource.Loading -> {
                    binding.searchPageLoadingBar.isVisible = true
                    binding.searchedFoodsHeaderText.isVisible = false
                    binding.searchedFoodsRecyclerView.isVisible = false
                    binding.searchedRestaurantsHeaderText.isVisible = false
                    binding.searchedRestaurantsRecyclerView.isVisible = false
                }

                is Resource.Error -> {
                    binding.searchPageLoadingBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Success -> {
                    binding.searchPageLoadingBar.isVisible = false

                    if (!it.data?.foodItems.isNullOrEmpty()) {
                        binding.searchedFoodsHeaderText.isVisible = true
                        binding.searchedFoodsRecyclerView.apply {
                            isVisible = true
                            adapter = SearchedFoodsRecyclerViewAdapter(
                                it.data?.foodItems!!,
                                this@SearchFragment
                            )
                        }
                    }

                    if (!it.data?.restaurants.isNullOrEmpty()) {
                        binding.searchedRestaurantsHeaderText.isVisible = true
                        binding.searchedRestaurantsRecyclerView.apply {
                            isVisible = true
                            adapter = SearchedRestaurantsRecyclerViewAdapter(it.data?.restaurants!!)
                        }
                    }
                }
            }
        }

        cartViewModel.addItemToCartLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addItemToCartClicked(itemId: String) {
        cartViewModel.addItemtoCart(itemId)
    }
}
