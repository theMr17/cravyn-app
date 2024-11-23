package com.cravyn.app.features.restaurant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemRestaurantMenuBinding
import com.cravyn.app.features.home.models.FoodItem

class RestaurantMenuRecyclerViewAdapter(
    private val restaurantMenuItemList: List<FoodItem>
) : RecyclerView.Adapter<RestaurantMenuRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRestaurantMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantMenuItemList[position]
    }

    override fun getItemCount() = restaurantMenuItemList.size
}
