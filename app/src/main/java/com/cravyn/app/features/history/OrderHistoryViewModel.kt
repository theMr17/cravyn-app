package com.cravyn.app.features.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.history.models.OrderHistoryResponse
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val orderHistoryRepository: OrderHistoryRepository
) : ViewModel() {
    private val _orderHistoryLiveData: MutableLiveData<Resource<OrderHistoryResponse>> =
        MutableLiveData()
    val orderHistoryLiveData: LiveData<Resource<OrderHistoryResponse>> get() = _orderHistoryLiveData


    private val _cancelOrderLiveData: MutableLiveData<Resource<Unit>> =
        MutableLiveData()
    val cancelOrderLiveData: LiveData<Resource<Unit>> get() = _cancelOrderLiveData

    fun getOrderHistory() {
        viewModelScope.launch {
            _orderHistoryLiveData.postValue(Resource.Loading())

            val response = orderHistoryRepository.getOrderHistory()

            if (response.isSuccessful) {
                _orderHistoryLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _orderHistoryLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }

    fun cancelOrder(orderId: String) {
        viewModelScope.launch {
            _cancelOrderLiveData.postValue(Resource.Loading())

            val response = orderHistoryRepository.cancelOrder(orderId)

            if (response.isSuccessful) {
                _cancelOrderLiveData.postValue(
                    Resource.Success(
                        data = response.body()!!.data,
                        message = response.body()?.message
                    )
                )
            } else {
                _cancelOrderLiveData.postValue(
                    Resource.Error(getErrorMessage(response))
                )
            }
        }
    }
}
