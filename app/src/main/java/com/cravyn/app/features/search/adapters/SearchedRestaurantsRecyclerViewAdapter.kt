package com.cravyn.app.features.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.data.api.toDisplayableNumber
import com.cravyn.app.databinding.ItemRecommendedRestaurantBinding
import com.cravyn.app.features.home.models.RecommendedRestaurantItem
import com.cravyn.app.features.search.models.SearchResponse

class SearchedRestaurantsRecyclerViewAdapter(
    private val searchedRestaurantsItemList: List<SearchResponse.Restaurant>
) : RecyclerView.Adapter<SearchedRestaurantsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRecommendedRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendedRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchedRestaurantsItemList[position]

        holder.binding.apply {
            restaurantNameText.text = item.name
            restaurantAddressText.text = item.city
            ratingDeliveryTimeText.text = "${item.rating}"

        }
    }

    override fun getItemCount() = searchedRestaurantsItemList.size
}
