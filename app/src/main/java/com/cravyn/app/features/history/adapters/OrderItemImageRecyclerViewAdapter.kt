package com.cravyn.app.features.history.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravyn.app.R
import com.cravyn.app.databinding.ItemOrderImageBinding
import com.cravyn.app.features.history.models.OrderHistoryResponse
import com.cravyn.app.util.toHttpsUrl

class OrderItemImageRecyclerViewAdapter(
    private val orderItemImageList: List<OrderHistoryResponse.Order.Item>
) : RecyclerView.Adapter<OrderItemImageRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemOrderImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orderItemImageList[position]

        holder.binding.apply {
            if (!item.foodImageUrl.isNullOrBlank()) {
                Glide.with(holder.itemView.context)
                    .load(item.foodImageUrl.toHttpsUrl())
                    .placeholder(R.drawable.restaurant_sample_image)
                    .error(R.drawable.restaurant_sample_image)
                    .into(orderItemImage)
            } else {
                Glide.with(holder.itemView.context).clear(orderItemImage)
            }

            orderItemCountText.text =
                holder.itemView.context.getString(R.string.order_item_count_text, item.quantity)
        }
    }

    override fun getItemCount() = orderItemImageList.size
}
