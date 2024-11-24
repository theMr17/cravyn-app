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
        val foodImageUrl: String,
        @SerializedName("food_name")
        val foodName: String,
        @SerializedName("item_id")
        val itemId: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("rating_count")
        val rating_count: Int,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("type")
        val type: String
    )

    data class Restaurant(
        @SerializedName("city")
        val city: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("gstin_url")
        val gstinUrl: Any?,
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
        @SerializedName("phone_number")
        val phoneNumber: String,
        @SerializedName("pin_code")
        val pinCode: String,
        @SerializedName("rating")
        val rating: Double,
        @SerializedName("rating_count")
        val rating_count: Int,
        @SerializedName("registration_no")
        val registrationNo: String,
        @SerializedName("restaurant_id")
        val restaurantId: String,
        @SerializedName("street")
        val street: String,
        @SerializedName("verify_status")
        val verifyStatus: String
    )
}