package com.cravyn.app.features.history.models

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

data class OrderHistoryResponse(
    @SerializedName("orders")
    val orders: List<Order>
) {
    data class Order(
        @SerializedName("address_id")
        val addressId: String,
        @SerializedName("can_cancel")
        val canCancel: Boolean,
        @SerializedName("checkout_price")
        val checkoutPrice: String,
        @SerializedName("customer_id")
        val customerId: String,
        @SerializedName("delivery_partner_name")
        val deliveryPartnerName: String,
        @SerializedName("display_address")
        val displayAddress: String,
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("list_id")
        val listId: String,
        @SerializedName("order_id")
        val orderId: String,
        @SerializedName("order_status")
        val orderStatus: String,
        @SerializedName("order_timestamp")
        val orderTimestamp: String,
        @SerializedName("partner_id")
        val partnerId: String,
        @SerializedName("product_image_url")
        val productImageUrl: Any?,
        @SerializedName("ratings")
        val ratings: Any?,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("restaurant_name")
        val restaurantName: String,
        @SerializedName("reviews")
        val reviews: Any?,
        @SerializedName("specifications")
        val specifications: String?
    ) {
        data class Item(
            @SerializedName("food_image_url")
            val foodImageUrl: String?,
            @SerializedName("quantity")
            val quantity: Int
        )
    }
}

fun String.toDisplayableTime(): String {
    return try {
        // Parse the ISO 8601 string to a Date object.
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        isoFormat.timeZone = TimeZone.getTimeZone("IST")
        val date = isoFormat.parse(this)

        // Format the Date object into a displayable string.
        val displayFormat = SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.getDefault())
        displayFormat.timeZone = TimeZone.getDefault() // Format in the device's local time zone.
        date?.let {
            displayFormat.format(date)
        } ?: this
    } catch (e: Exception) {
        this
    }
}

