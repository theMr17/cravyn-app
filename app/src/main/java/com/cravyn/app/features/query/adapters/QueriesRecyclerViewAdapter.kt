package com.cravyn.app.features.query.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cravyn.app.R
import com.cravyn.app.databinding.ItemQueryBinding
import com.cravyn.app.features.query.models.GetQueriesResponse

class QueriesRecyclerViewAdapter(
    private val queryItemList: List<GetQueriesResponse.CustomerQuery>
) : RecyclerView.Adapter<QueriesRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemQueryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQueryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = queryItemList[position]

        holder.binding.apply {
            questionText.text = item.question
            if (item.answer.isNullOrBlank()) {
                answerText.text =
                    holder.itemView.context.getString(R.string.query_answer_not_available_text)
                answeredByText.isVisible = false
            } else {
                answerText.text = item.answer
                answeredByText.text =
                    holder.itemView.context.getString(R.string.answered_by_text, item.managerName)
            }
        }
    }

    override fun getItemCount() = queryItemList.size
}
