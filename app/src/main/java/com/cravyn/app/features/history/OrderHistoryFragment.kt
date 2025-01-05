package com.cravyn.app.features.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cravyn.app.data.api.Resource
import com.cravyn.app.databinding.FragmentOrderHistoryBinding
import com.cravyn.app.features.history.adapters.OrderHistoryRecyclerViewAdapter
import com.cravyn.app.features.history.listeners.CancelOrderItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderHistoryFragment : Fragment(), CancelOrderItemClickListener {
    private var _binding: FragmentOrderHistoryBinding? = null
    private val binding get() = _binding!!

    private val orderHistoryViewModel: OrderHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)

        orderHistoryViewModel.getOrderHistory()

        orderHistoryViewModel.orderHistoryLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    binding.orderHistoryRecyclerView.adapter =
                        OrderHistoryRecyclerViewAdapter(
                            it.data?.orders ?: emptyList(),
                            this@OrderHistoryFragment
                        )
                }
            }
        }

        orderHistoryViewModel.cancelOrderLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}

                is Resource.Success -> {
                    orderHistoryViewModel.getOrderHistory()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCancelOrderItemClicked(orderId: String) {
        orderHistoryViewModel.cancelOrder(orderId)
    }
}
