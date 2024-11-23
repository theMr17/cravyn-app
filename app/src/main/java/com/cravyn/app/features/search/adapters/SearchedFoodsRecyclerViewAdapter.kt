package com.cravyn.app.features.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.databinding.ItemRestaurantMenuBinding
import com.cravyn.app.features.home.models.FoodItem

class SearchedFoodsRecyclerViewAdapter(
    private val searchedFoodsItemList: List<FoodItem>
) : RecyclerView.Adapter<SearchedFoodsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchedFoodsItemList[position]

        holder.binding.foodDescriptionText.text = "Arsalan"
    }

    override fun getItemCount() = searchedFoodsItemList.size
}
