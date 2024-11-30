package com.cravyn.app.features.cart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravyn.app.R
import com.cravyn.app.databinding.ItemCartBinding
import com.cravyn.app.features.cart.listener.UpdateItemStatusListener
import com.cravyn.app.features.cart.model.CartResponse
import com.cravyn.app.util.toHttpsUrl

class CartRecyclerViewAdapter(
    private val cartItemList: List<CartResponse.Cart>,
    private val updateItemStatusListener: UpdateItemStatusListener
) : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cartItemList[position]

        holder.binding.apply {
            foodNameText.text = item.foodName
            quantityText.text = item.quantity.toString()
            originalPriceText.text =
                holder.itemView.context.getString(
                    R.string.formatted_price_text,
                    item.foodPrice
                )
            if (item.foodDiscountPercent.isNullOrBlank()) {
                finalPriceText.isVisible = false
                originalPriceText.foreground = null
            } else {
                finalPriceText.text = holder.itemView.context.getString(
                    R.string.formatted_price_text,
                    item.finalDiscountedPrice.toString()
                )
            }
            if (!item.foodImageUrl.isNullOrBlank()) {
                Glide.with(holder.itemView.context)
                    .load(item.foodImageUrl.toHttpsUrl())
                    .placeholder(R.drawable.restaurant_sample_image)
                    .error(R.drawable.restaurant_sample_image)
                    .into(foodImage)
            } else {
                Glide.with(holder.itemView.context).clear(foodImage)
            }

            plusButton.setOnClickListener {
                updateItemStatusListener.incrementItemClicked(item.itemId)
            }

            minusButton.setOnClickListener {
                updateItemStatusListener.decrementItemClicked(item.itemId)
            }

            removeButton.setOnClickListener {
                updateItemStatusListener.deleteItemClicked(item.itemId)
            }
        }
    }

    override fun getItemCount() = cartItemList.size
}
