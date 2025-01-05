package com.cravyn.app.features.cart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravyn.app.R
import com.cravyn.app.data.api.toDisplayableNumber
import com.cravyn.app.databinding.ItemCartBinding
import com.cravyn.app.features.cart.listeners.UpdateCartItemStatusListener
import com.cravyn.app.features.cart.models.CartResponse
import com.cravyn.app.util.toHttpsUrl

class CartRecyclerViewAdapter(
    private val cartItemList: List<CartResponse.Cart>,
    private val updateCartItemStatusListener: UpdateCartItemStatusListener
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
                    item.foodPrice.toDouble().toDisplayableNumber().formatted
                )

            if (item.foodDiscountPercent.isNullOrBlank()) {
                finalPriceText.isVisible = false
                originalPriceText.foreground = null
            } else {
                finalPriceText.text = holder.itemView.context.getString(
                    R.string.formatted_price_text,
                    item.finalDiscountedPrice.toDisplayableNumber().formatted
                )
            }

            if (item.foodImageUrl.isNotBlank()) {
                Glide.with(holder.itemView.context)
                    .load(item.foodImageUrl.toHttpsUrl())
                    .placeholder(R.drawable.sample_image)
                    .error(R.drawable.sample_image)
                    .into(foodImage)
            } else {
                Glide.with(holder.itemView.context).clear(foodImage)
            }

            plusButton.setOnClickListener {
                updateCartItemStatusListener.onPlusButtonClicked(item.itemId)
            }

            minusButton.setOnClickListener {
                updateCartItemStatusListener.onMinusButtonClicked(item.itemId)
            }

            removeButton.setOnClickListener {
                updateCartItemStatusListener.onRemoveItemButtonClicked(item.itemId)
            }
        }
    }

    override fun getItemCount() = cartItemList.size
}
