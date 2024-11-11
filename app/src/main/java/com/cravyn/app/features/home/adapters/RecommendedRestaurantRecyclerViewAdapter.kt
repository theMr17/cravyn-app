package com.cravyn.app.features.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.features.auth.models.RestaurantItem

class RecommendedRestaurantRecyclerViewAdapter(
    private val restaurantItemList: List<RestaurantItem>
) : RecyclerView.Adapter<RecommendedRestaurantRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantImg = itemView.findViewById<ImageView>(R.id.restaurant_image)
        val offer = itemView.findViewById<TextView>(R.id.discount_percent_text)
        val maxOffer = itemView.findViewById<TextView>(R.id.discount_max_text)
        val bestInItem = itemView.findViewById<TextView>(R.id.best_in_item_name)
        val ratingOfRestaurant = itemView.findViewById<TextView>(R.id.rating_delivery_time_text)
        val address = itemView.findViewById<TextView>(R.id.restaurant_address_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended_restaurant, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantItemList[position]

        holder.restaurantImg.setImageResource(item.restaurantImage)
        holder.offer.text = item.offerAmount
        holder.maxOffer.text = item.maxOfferAmount
        holder.bestInItem.text = item.bestInItemName
        holder.address.text = item.restaurantAddress
    }

    override fun getItemCount() = restaurantItemList.size
}
