package com.cravyn.app.features.address.models


import com.google.gson.annotations.SerializedName

data class SavedAddressesResponse(
    @SerializedName("address")
    val addresses: List<Address>
) {
    data class Address(
        @SerializedName("address_id")
        val addressId: String,
        @SerializedName("customer_id")
        val customerId: String,
        @SerializedName("display_address")
        val displayAddress: String,
        @SerializedName("is_default")
        val isDefault: Boolean,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("longitude")
        val longitude: String
    )
}
