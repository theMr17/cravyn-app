package com.cravyn.app.features.restaurant.models

import com.cravyn.app.data.api.DisplayableNumber
import com.cravyn.app.features.home.models.RecommendedRestaurantItem
import java.io.Serializable

data class Restaurant(
    val restaurantId: String,
    val name: String,
    val distance: DisplayableNumber,
    val minTime: Int,
    val maxTime: Int,
    val ratingCount: Int,
    val rating: DisplayableNumber
) : Serializable

fun RecommendedRestaurantItem.toRestaurant(): Restaurant {
    return Restaurant(
        restaurantId = this.restaurantId,
        name = this.name,
        distance = this.distance,
        minTime = this.minTime,
        maxTime = this.maxTime,
        ratingCount = this.ratingCount,
        rating = this.rating,
    )
}
