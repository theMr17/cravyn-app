package com.cravyn.app.features.cart.model


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("cart")
    val cart: List<Cart>,
    @SerializedName("deliveryCharge")
    val deliveryCharge: Int,
    @SerializedName("finalPrice")
    val finalPrice: Double,
    @SerializedName("platformCharge")
    val platformCharge: Int,
    @SerializedName("totalDiscount")
    val totalDiscount: Double,
    @SerializedName("totalPrice")
    val totalPrice: Int
) {
    data class Cart(
        @SerializedName("final_discounted_price")
        val finalDiscountedPrice: Double,
        @SerializedName("food_description")
        val foodDescription: String,
        @SerializedName("food_discount_cap")
        val foodDiscountCap: String?,
        @SerializedName("food_discount_percent")
        val foodDiscountPercent: String?,
        @SerializedName("food_image_url")
        val foodImageUrl: String,
        @SerializedName("food_name")
        val foodName: String,
        @SerializedName("food_price")
        val foodPrice: String,
        @SerializedName("item_id")
        val itemId: String,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("restaurant_name")
        val restaurantName: String
    )
}
