package com.cravyn.app.features.history.models


import com.google.gson.annotations.SerializedName

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
            val foodImageUrl: String,
            @SerializedName("quantity")
            val quantity: Int
        )
    }
}
