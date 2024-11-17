package com.cravyn.app.features.restaurant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.features.home.models.FoodItem

class RestaurantMenuRecyclerViewAdapter(
    private val restaurantMenuItemList: List<FoodItem>
) : RecyclerView.Adapter<RestaurantMenuRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantMenuItemList[position]
    }

    override fun getItemCount() = restaurantMenuItemList.size
}
