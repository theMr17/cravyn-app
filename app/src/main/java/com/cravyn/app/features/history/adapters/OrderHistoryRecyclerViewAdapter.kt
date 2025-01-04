package com.cravyn.app.features.history.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.databinding.ItemOrderHistoryBinding
import com.cravyn.app.features.history.models.OrderHistoryResponse

class OrderHistoryRecyclerViewAdapter(
    private val orderHistoryList: List<OrderHistoryResponse.Order>
) : RecyclerView.Adapter<OrderHistoryRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemOrderHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orderHistoryList[position]

        holder.binding.apply {
            orderIdText.text =
                holder.itemView.context.getString(R.string.order_id_text, item.orderId)
            orderNoText.text = holder.itemView.context.getString(R.string.order_no_text, item.listId)
            finalPriceText.text = holder.itemView.context.getString(R.string.formatted_price_text, item.checkoutPrice)
            statusText.text = item.orderStatus
            addressText.text = item.displayAddress

            if (item.canCancel) {
                cancelOrderButton.visibility = View.VISIBLE
            } else {
                cancelOrderButton.visibility = View.INVISIBLE
            }

            orderHistoryImagesRecyclerView.adapter = OrderItemImageRecyclerViewAdapter(item.items)
        }
    }

    override fun getItemCount() = orderHistoryList.size
}
