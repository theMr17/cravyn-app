package com.cravyn.app.features.search.models


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("foodItems")
    val foodItems: List<FoodItem>,
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>
) {
    data class FoodItem(
        @SerializedName("description")
        val description: String,
        @SerializedName("discount_cap")
        val discountCap: Any?,
        @SerializedName("discount_percent")
        val discountPercent: Any?,
        @SerializedName("food_image_url")
        val foodImageUrl: String?,
        @SerializedName("food_name")
        val foodName: String,
        @SerializedName("item_id")
        val itemId: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("rating_count")
        val ratingCount: Int,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("restaurant_name")
        val restaurantName: String,
        @SerializedName("type")
        val type: String
    )

    data class Restaurant(
        @SerializedName("availability_status")
        val availabilityStatus: Boolean,
        @SerializedName("avg_rating")
        val avgRating: Double,
        @SerializedName("city")
        val city: String,
        @SerializedName("latitude")
        val latitude: Double,
        @SerializedName("longitude")
        val longitude: Double,
        @SerializedName("max_discount_cap")
        val maxDiscountCap: Double?,
        @SerializedName("max_discount_percent")
        val maxDiscountPercent: Double?,
        @SerializedName("name")
        val name: String,
        @SerializedName("pin_code")
        val pinCode: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("rating_count")
        val ratingCount: Int,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("restaurant_image_url")
        val restaurantImageUrl: String?,
        @SerializedName("street")
        val street: String
    )
}
