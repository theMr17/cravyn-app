package com.cravyn.app.features.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cravyn.app.R
import com.cravyn.app.features.home.models.FoodItem

class RecommendedFoodGridViewAdapter(
    private val context: Context,
    private val items: List<FoodItem>
) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val view =
            convertView ?: layoutInflater.inflate(R.layout.item_recommended_food, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.food_image)
        val textView = view.findViewById<TextView>(R.id.food_name_text)

        val item = items[position]
        imageView.setImageResource(item.imageResId)
        textView.text = item.title

        return view
    }

}
