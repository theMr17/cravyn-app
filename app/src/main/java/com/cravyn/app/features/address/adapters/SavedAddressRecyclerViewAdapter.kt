package com.cravyn.app.features.address.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemSavedAddressBinding
import com.cravyn.app.features.address.listeners.RemoveAddressItemClickListener
import com.cravyn.app.features.address.listeners.SaveAddressAsDefaultItemClickListener
import com.cravyn.app.features.address.models.SavedAddressesResponse

class SavedAddressRecyclerViewAdapter(
    private val savedAddresses: List<SavedAddressesResponse.Address>,
    private val removeAddressItemClickListener: RemoveAddressItemClickListener,
    private val setAddressAsDefaultItemClickListener: SaveAddressAsDefaultItemClickListener
) : RecyclerView.Adapter<SavedAddressRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSavedAddressBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSavedAddressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = savedAddresses[position]

        holder.binding.apply {
            addressText.text = item.displayAddress
            setAsDefaultButton.isVisible = !item.isDefault

            removeAddressButton.setOnClickListener {
                removeAddressItemClickListener.onRemoveButtonClicked(item.addressId)
            }

            setAsDefaultButton.setOnClickListener {
                setAddressAsDefaultItemClickListener.onSaveAsDefaultButtonClicked(item.addressId)
            }
        }
    }

    override fun getItemCount() = savedAddresses.size
}
