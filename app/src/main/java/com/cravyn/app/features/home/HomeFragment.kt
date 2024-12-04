package com.cravyn.app.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.R
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentHomeBinding
import com.cravyn.app.features.address.AddressViewModel
import com.cravyn.app.features.address.saved.SavedAddressActivity.Companion.createSavedAddressActivity
import com.cravyn.app.features.home.adapters.RecommendedFoodGridViewAdapter
import com.cravyn.app.features.home.adapters.RecommendedRestaurantRecyclerViewAdapter
import com.cravyn.app.features.home.listeners.RecommendedRestaurantItemClickListener
import com.cravyn.app.features.home.models.FoodItem
import com.cravyn.app.features.profile.ProfileActivity.Companion.createProfileActivity
import com.cravyn.app.features.query.QueryActivity.Companion.createQueryActivity
import com.cravyn.app.features.search.SearchActivity.Companion.createSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.selectedAddressContainer.setOnClickListener {
            startActivity(createSavedAddressActivity(requireContext()))
        }

        binding.searchTextInputLayout.editText?.setOnClickListener {
            startActivity(createSearchActivity(requireContext()))
        }

        binding.recommendedFoodHeaderText.setOnClickListener {
            startActivity(createQueryActivity(requireContext()))
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

        gridView.setOnItemClickListener { _, _, position, _ ->
            startActivity(createSearchActivity(requireContext(), foodItem[position].title))
        }

        binding.sortByButton.setOnClickListener { view ->
            showRecommendedRestaurantSortByMenu(view)
        }

        binding.yourAccountButton.setOnClickListener {
            startActivity(createProfileActivity(requireContext()))
        }

        binding.orderNowButton.setOnClickListener {
            startActivity(createSearchActivity(requireContext()))
        }

        homeViewModel.getRecommendedRestaurants()
        homeViewModel.recommendedRestaurantsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding.recommendedRestaurantsLoadingBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    binding.recommendedRestaurantsLoadingBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.recommendedRestaurantsLoadingBar.isVisible = false
                    binding.recommendedRestaurantRecyclerView.adapter =
                        RecommendedRestaurantRecyclerViewAdapter(
                            it.data ?: emptyList(),
                            requireActivity() as RecommendedRestaurantItemClickListener
                        )
                }
            }
        }

        addressViewModel.defaultAddressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    binding.selectedAddressText.text = it.data?.addresses?.get(0)?.displayAddress
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        addressViewModel.getDefaultAddress()
    }

    private fun showRecommendedRestaurantSortByMenu(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(R.menu.sort_recommended_restaurants_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.distance_option -> {
                    homeViewModel.getRecommendedRestaurants(sortBy = "distance")
                    true
                }

                R.id.rating_option -> {
                    homeViewModel.getRecommendedRestaurants(sortBy = "rating", descending = true)
                    true
                }

                R.id.discount_option -> {
                    homeViewModel.getRecommendedRestaurants(sortBy = "discount_percent")
                    true
                }

                else -> false
            }
        }
        popup.setOnDismissListener { }

        popup.show()
    }
}
