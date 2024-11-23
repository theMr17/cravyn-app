package com.cravyn.app.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemRecommendedRestaurantBinding
import com.cravyn.app.features.home.listeners.RecommendedRestaurantItemClickListener
import com.cravyn.app.features.home.models.RecommendedRestaurantItem
import com.cravyn.app.features.restaurant.models.toRestaurant

class RecommendedRestaurantRecyclerViewAdapter(
    private val restaurantItemList: List<RecommendedRestaurantItem>,
    private val recommendedRestaurantItemClickListener: RecommendedRestaurantItemClickListener
) : RecyclerView.Adapter<RecommendedRestaurantRecyclerViewAdapter.ViewHolder>() {

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

            ratingDeliveryTimeText.text =
                "${item.rating.formatted} (${item.ratingCount}) • ${item.minTime}-${item.maxTime} min"
            restaurantAddressText.text = "${item.city} | ${item.distance.formatted} km"

            root.setOnClickListener {
                recommendedRestaurantItemClickListener.onRecommendedRestaurantItemClicked(item.toRestaurant())
            }
        }
    }

    override fun getItemCount() = restaurantItemList.size
}
