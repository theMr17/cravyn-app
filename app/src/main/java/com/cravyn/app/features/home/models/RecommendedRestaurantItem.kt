package com.cravyn.app.features.home.models

import android.icu.text.NumberFormat
import java.util.Locale

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

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Double.toDisplayableNumber(digitsAfterDecimal: Int = 2): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = digitsAfterDecimal
        maximumFractionDigits = digitsAfterDecimal
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}
