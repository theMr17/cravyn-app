package com.cravyn.app.features.home.models

data class RestaurantItem(
    val restaurantImage: Int,
    val offerAmount: String,
    val maxOfferAmount: String,
    val bestInItemName: String,
    val rating: String,
    val deliveryTime: String,
    val restaurantAddress: String
)
