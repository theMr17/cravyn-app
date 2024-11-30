package com.cravyn.app.features.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravyn.app.R
import com.cravyn.app.data.api.toDisplayableNumber
import com.cravyn.app.databinding.ItemRestaurantMenuBinding
import com.cravyn.app.features.cart.listeners.AddItemToCartItemClickListener
import com.cravyn.app.features.search.models.SearchResponse
import com.cravyn.app.util.toHttpsUrl

class SearchedFoodsRecyclerViewAdapter(
    private val searchedFoodsItemList: List<SearchResponse.FoodItem>,
    private val addItemToCartItemClickListener: AddItemToCartItemClickListener
) : RecyclerView.Adapter<SearchedFoodsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchedFoodsItemList[position]

        holder.binding.apply {
            foodNameText.text = item.foodName

            priceText.text = holder.itemView.context.getString(
                R.string.search_food_item_price_text,
                item.price
            )

            ratingText.text =
                holder.itemView.context.getString(
                    R.string.search_food_item_rating_text,
                    item.rating.toDisplayableNumber(1).formatted,
                    item.ratingCount
                )

            foodDescriptionText.text = item.restaurantName

            if (!item.foodImageUrl.isNullOrBlank()) {
                Glide.with(holder.itemView.context)
                    .load(item.foodImageUrl.toHttpsUrl())
                    .placeholder(R.drawable.restaurant_sample_image)
                    .error(R.drawable.restaurant_sample_image)
                    .into(foodImage)
            } else {
                Glide.with(holder.itemView.context).clear(foodImage)
            }

            addToCartButton.setOnClickListener {
                addItemToCartItemClickListener.addItemToCartClicked(item.itemId)
            }
        }
    }

    override fun getItemCount() = searchedFoodsItemList.size
}
