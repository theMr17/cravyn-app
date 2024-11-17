package com.cravyn.app.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemRecommendedRestaurantBinding
import com.cravyn.app.features.home.models.RecommendedRestaurantItem

class RecommendedRestaurantRecyclerViewAdapter(
    private val restaurantItemList: List<RecommendedRestaurantItem>
) : RecyclerView.Adapter<RecommendedRestaurantRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRecommendedRestaurantBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendedRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantItemList[position]

        holder.binding.apply {
            restaurantNameText.text = item.name

            item.maxDiscountPercent?.let { maxDiscount ->
                discountPercentText.text = "${maxDiscount.formatted}% OFF"
            } ?: run {
                discountPercentText.isVisible = false
            }

            item.maxDiscountCap?.formatted?.let { maxDiscountCap ->
                discountMaxText.text = "Up to ₹$maxDiscountCap"
            } ?: run {
                discountMaxText.isVisible = false
            }
            
            ratingDeliveryTimeText.text = "${item.rating.formatted} (10K+) • ${item.minTime}-${item.maxTime} min"
            restaurantAddressText.text = "${item.city} | ${item.distance.formatted} km"
        }
    }

    override fun getItemCount() = restaurantItemList.size
}
