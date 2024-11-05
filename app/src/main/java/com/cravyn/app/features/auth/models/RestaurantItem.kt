package com.cravyn.app.features.auth.models

import android.widget.ImageView

data class RestaurantItem (
    val restaurantImage : Int,
    val offerAmount : String,
    val maxOfferAmount : String,
    val bestInItemName : String,
    val rating : String,
    val deliveryTime : String,
    val restaurantAddress : String
)