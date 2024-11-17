package com.cravyn.app.features.home.models


import com.google.gson.annotations.SerializedName

data class RecommendedRestaurantsResponse(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>
) {
    data class Restaurant(
        @SerializedName("availability_status")
        val availabilityStatus: Boolean,
        @SerializedName("city")
        val city: String,
        @SerializedName("distance")
        val distance: Double,
        @SerializedName("landmark")
        val landmark: String,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("max_discount_percent")
        val maxDiscountPercent: Double?,
        @SerializedName("max_discount_cap")
        val maxDiscountCap: Double?,
        @SerializedName("maxTime")
        val maxTime: Int,
        @SerializedName("minTime")
        val minTime: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("pin_code")
        val pinCode: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("rating_number")
        val ratingCount: Int,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("restaurant_image_url")
        val restaurantImageUrl: String?,
        @SerializedName("street")
        val street: String
    )
}
