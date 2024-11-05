package com.cravyn.app.features.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.features.auth.models.RestaurantItem

class RecommandedRestaurantAdapter(
    private val restaurantItemList : List<RestaurantItem>
) : RecyclerView.Adapter<RecommandedRestaurantAdapter.ViewHolder>() {

    class ViewHolder(RestaurantItemView: View): RecyclerView.ViewHolder(RestaurantItemView) {
        val restaurantImg = RestaurantItemView.findViewById<ImageView>(R.id.restaurant_image_sample)
        val offer = RestaurantItemView.findViewById<TextView>(R.id.restaurant_offer)
        val maxOffer = RestaurantItemView.findViewById<TextView>(R.id.max_offer)
        val bestInItem = RestaurantItemView.findViewById<TextView>(R.id.best_in_item_name)
        val ratingOfRestaurant = RestaurantItemView.findViewById<TextView>(R.id.rating)
        val delivery = RestaurantItemView.findViewById<TextView>(R.id.delivery_time)
        val address = RestaurantItemView.findViewById<TextView>(R.id.restaurant_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_restaurant_view_card, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantItemList[position]
        holder.restaurantImg.setImageResource(item.restaurantImage)
        holder.offer.text = item.offerAmount
        holder.maxOffer.text = item.maxOfferAmount
        holder.bestInItem.text = item.bestInItemName
        holder.delivery.text = item.deliveryTime
        holder.address.text = item.restaurantAddress

    }

    override fun getItemCount() = restaurantItemList.size


}