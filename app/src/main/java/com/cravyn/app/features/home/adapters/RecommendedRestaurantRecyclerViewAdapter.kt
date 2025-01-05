package com.cravyn.app.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravyn.app.R
import com.cravyn.app.databinding.ItemRecommendedRestaurantBinding
import com.cravyn.app.features.home.listeners.RecommendedRestaurantItemClickListener
import com.cravyn.app.features.home.models.RecommendedRestaurantItem
import com.cravyn.app.features.restaurant.models.toRestaurant
import com.cravyn.app.util.toHttpsUrl

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
                discountPercentText.text = holder.itemView.context.getString(
                    R.string.item_restaurant_discount_percent_text,
                    maxDiscount.formatted
                )
            } ?: run {
                discountPercentText.isVisible = false
            }

            item.maxDiscountCap?.formatted?.let { maxDiscountCap ->
                discountMaxText.text =
                    holder.itemView.context.getString(
                        R.string.item_restaurant_discount_max_text,
                        maxDiscountCap
                    )
            } ?: run {
                discountMaxText.isVisible = false
            }

            ratingDeliveryTimeText.text =
                holder.itemView.context.getString(
                    R.string.item_restaurant_rating_delivery_time_text,
                    item.rating.formatted,
                    item.ratingCount,
                    item.minTime,
                    item.maxTime
                )
            restaurantAddressText.text = holder.itemView.context.getString(
                R.string.item_restaurant_address_text,
                item.city,
                item.distance.formatted
            )

            if (!item.restaurantImageUrl.isNullOrBlank()) {
                Glide.with(holder.itemView.context)
                    .load(item.restaurantImageUrl.toHttpsUrl())
                    .placeholder(R.drawable.sample_image)
                    .error(R.drawable.sample_image)
                    .into(restaurantImage)
            } else {
                Glide.with(holder.itemView.context).clear(restaurantImage)
            }

            root.setOnClickListener {
                recommendedRestaurantItemClickListener.onRecommendedRestaurantItemClicked(item.toRestaurant())
            }
        }
    }

    override fun getItemCount() = restaurantItemList.size
}
