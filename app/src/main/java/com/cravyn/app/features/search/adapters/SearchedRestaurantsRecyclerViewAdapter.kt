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

            item.maxDiscountPercent?.let { maxDiscount ->
                discountPercentText.text = holder.itemView.context.getString(
                    R.string.item_restaurant_discount_percent_text,
                    maxDiscount.toDisplayableNumber(0).formatted
                )
            } ?: run {
                discountPercentText.isVisible = false
            }

            item.maxDiscountCap?.toDisplayableNumber(0)?.formatted?.let { maxDiscountCap ->
                discountMaxText.text = holder.itemView.context.getString(
                    R.string.item_restaurant_discount_max_text,
                    maxDiscountCap
                )
            } ?: run {
                discountMaxText.isVisible = false
            }

            ratingDeliveryTimeText.text =
                holder.itemView.context.getString(
                    R.string.item_restaurant_rating_text,
                    item.rating.toDisplayableNumber(2).formatted,
                    item.ratingCount
                )
            restaurantAddressText.isVisible = false
        }
    }

    override fun getItemCount() = searchedRestaurantsItemList.size
}
