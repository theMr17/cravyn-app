package com.cravyn.app.features.address.listeners

interface SaveAddressItemClickListener {
    fun onSaveButtonClicked(latitude: Double, longitude: Double, displayAddress: String)
}
