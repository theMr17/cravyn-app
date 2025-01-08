package com.cravyn.app.features.history.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.databinding.ItemOrderHistoryBinding
import com.cravyn.app.features.history.listeners.CancelOrderItemClickListener
import com.cravyn.app.features.history.listeners.RepeatOrderButtonClickedListener
import com.cravyn.app.features.history.models.OrderHistoryResponse
import com.cravyn.app.features.history.models.toDisplayableTime

class OrderHistoryRecyclerViewAdapter(
    private val orderHistoryList: List<OrderHistoryResponse.Order>,
    private val cancelOrderItemClickListener: CancelOrderItemClickListener,
    private val repeatOrderListener: RepeatOrderButtonClickedListener
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
            cancelOrderButton.visibility = View.INVISIBLE
            repeatOrderButton.visibility = View.INVISIBLE

            orderTimeText.text = item.orderTimestamp.toDisplayableTime()
            orderNoText.text = holder.itemView.context.getString(R.string.order_no_text, item.listId)
            finalPriceText.text = holder.itemView.context.getString(R.string.formatted_price_text, item.checkoutPrice)
            statusText.text = item.orderStatus
            addressText.text = item.displayAddress
            deliveryPartnerNameText.text = item.deliveryPartnerName

            if (item.canCancel) {
                cancelOrderButton.visibility = View.VISIBLE
            } else {
                cancelOrderButton.visibility = View.INVISIBLE
            }

            if (item.canRepeat) {
                repeatOrderButton.visibility = View.VISIBLE
            } else {
                repeatOrderButton.visibility = View.INVISIBLE
            }

            cancelOrderButton.setOnClickListener {
                cancelOrderItemClickListener.onCancelOrderButtonClicked(item.orderId)
            }

            repeatOrderButton.setOnClickListener {
                repeatOrderListener.onRepeatOrderButtonClicked(item.orderId)
            }

            orderHistoryImagesRecyclerView.adapter = OrderItemImageRecyclerViewAdapter(item.items)
        }
    }

    override fun getItemCount() = orderHistoryList.size
}
