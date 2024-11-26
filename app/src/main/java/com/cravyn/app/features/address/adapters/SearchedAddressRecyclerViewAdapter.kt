package com.cravyn.app.features.address.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemSearchedAddressBinding
import com.cravyn.app.features.address.listeners.SaveAddressItemClickListener
import com.cravyn.app.features.address.models.SearchedAddressResponseItem

class SearchedAddressRecyclerViewAdapter(
    private val searchedAddresses: List<SearchedAddressResponseItem>,
    private val saveAddressItemClickListener: SaveAddressItemClickListener
) : RecyclerView.Adapter<SearchedAddressRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSearchedAddressBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchedAddressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchedAddresses[position]

        holder.binding.apply {
            addressText.text = item.displayName
            saveAddressButton.setOnClickListener {
                saveAddressItemClickListener.onSaveButtonClicked(
                    item.lat.toDouble(),
                    item.lon.toDouble(),
                    item.displayName
                )
            }
        }
    }

    override fun getItemCount() = searchedAddresses.size
}
