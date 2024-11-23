package com.cravyn.app.features.home.models

import com.cravyn.app.data.api.DisplayableNumber
import com.cravyn.app.data.api.toDisplayableNumber

data class RecommendedRestaurantItem(
    val restaurantId: String,
    val name: String,
    val city: String,
    val distance: DisplayableNumber,
    val maxDiscountPercent: DisplayableNumber?,
    val maxDiscountCap: DisplayableNumber?,
    val minTime: Int,
    val maxTime: Int,
    val ratingCount: Int,
    val rating: DisplayableNumber,
    val restaurantImageUrl: String?
)

fun RecommendedRestaurantsResponse.Restaurant.toRecommendedRestaurantItem(): RecommendedRestaurantItem {
    return RecommendedRestaurantItem(
        restaurantId = this.restaurantId,
        name = this.name,
        city = this.city,
        distance = this.distance.toDisplayableNumber(),
        minTime = this.minTime,
        maxTime = this.maxTime,
        rating = this.rating.toDisplayableNumber(1),
        ratingCount = ratingCount,
        maxDiscountPercent = this.maxDiscountPercent?.toDisplayableNumber(0),
        maxDiscountCap = this.maxDiscountCap?.toDisplayableNumber(0),
        restaurantImageUrl = this.restaurantImageUrl
    )
}
