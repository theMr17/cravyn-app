package com.cravyn.app.features.address.models

import com.google.gson.annotations.SerializedName

data class SearchedAddressResponseItem(
    @SerializedName("boundingbox")
    val boundingBox: List<String>,
    @SerializedName("class")
    val classX: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("importance")
    val importance: Double,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("osm_id")
    val osmId: Long,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("place_id")
    val placeId: Int,
    @SerializedName("type")
    val type: String
)
