package com.cravyn.app.features.address.models


import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("address")
    val address: Address,
    @SerializedName("boundingbox")
    val boundingBox: List<String>,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("osm_id")
    val osmId: Int,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("place_id")
    val placeId: Int
) {
    data class Address(
        @SerializedName("amenity")
        val amenity: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("country_code")
        val countryCode: String,
        @SerializedName("county")
        val county: String,
        @SerializedName("ISO3166-2-lvl4")
        val iSO31662Lvl4: String,
        @SerializedName("neighbourhood")
        val neighbourhood: String,
        @SerializedName("postcode")
        val postcode: String,
        @SerializedName("road")
        val road: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("state_district")
        val stateDistrict: String,
        @SerializedName("suburb")
        val suburb: String
    )
}
