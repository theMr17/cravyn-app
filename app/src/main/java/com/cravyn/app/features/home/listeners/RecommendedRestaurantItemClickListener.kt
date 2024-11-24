package com.cravyn.app.features.home.listeners

import com.cravyn.app.features.restaurant.models.Restaurant

interface RecommendedRestaurantItemClickListener {
    fun onRecommendedRestaurantItemClicked(restaurant: Restaurant)
}
