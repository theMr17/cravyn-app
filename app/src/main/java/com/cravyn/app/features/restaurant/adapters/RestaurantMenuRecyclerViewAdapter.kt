package com.cravyn.app.features.restaurant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemRestaurantMenuBinding
import com.cravyn.app.features.restaurant.models.RestaurantMenuResponse

class RestaurantMenuRecyclerViewAdapter(
    private val restaurantMenuItemList: List<RestaurantMenuResponse.Catalog>
) : RecyclerView.Adapter<RestaurantMenuRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantMenuItemList[position]

        holder.binding.apply {
            foodNameText.text = item.foodName
            foodDescriptionText.text = item.description
            priceText.text = "₹${item.price}"
        }
    }

    override fun getItemCount() = restaurantMenuItemList.size
}
