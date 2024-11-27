package com.cravyn.app.features.restaurant.models


import com.google.gson.annotations.SerializedName

data class RestaurantMenuResponse(
    @SerializedName("catalog")
    val catalog: List<Catalog>,
    @SerializedName("restaurant")
    val restaurant: Restaurant
) {
    data class Catalog(
        @SerializedName("description")
        val description: String,
        @SerializedName("discount_cap")
        val discountCap: String?,
        @SerializedName("discount_percent")
        val discountPercent: String?,
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
        @SerializedName("type")
        val type: String
    )

    data class Restaurant(
        @SerializedName("city")
        val city: String,
        @SerializedName("landmark")
        val landmark: String,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("license_url")
        val licenseUrl: String,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("owner_id")
        val ownerId: String,
        @SerializedName("pin_code")
        val pinCode: String,
        @SerializedName("registration_no")
        val registrationNo: String,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("street")
        val street: String
    )
}
