package com.cravyn.app.features.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.features.cart.model.DecrementItemCountRequestBody
import com.cravyn.app.features.cart.model.CartResponse
import com.cravyn.app.features.cart.model.IncrementItemCountRequestBody
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _addItemToCartLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val addItemToCartLiveData: LiveData<Resource<Unit>> = _addItemToCartLiveData

    private val _cartLiveData: MutableLiveData<Resource<CartResponse>> = MutableLiveData()
    val cartLiveData: LiveData<Resource<CartResponse>> = _cartLiveData

    fun addItemToCart(itemId: String) {
        viewModelScope.launch {
            _addItemToCartLiveData.postValue(Resource.Loading())

            val addItemToCartRequestBody = AddItemtoCartRequestBody(itemId)
            val addItemToCartResponse = cartRepository.addItemToCart(addItemToCartRequestBody)

            if (addItemToCartResponse.isSuccessful) {
                _addItemToCartLiveData.postValue(
                    Resource.Success(
                        data = Unit,
                        message = addItemToCartResponse.body()?.message
                    )
                )
            } else {
                _addItemToCartLiveData.postValue(
                    Resource.Error(getErrorMessage(addItemToCartResponse))
                )
            }
        }
    }

    fun getCart() {
        viewModelScope.launch {
            _cartLiveData.postValue((Resource.Loading()))

            val getCartResponse = cartRepository.getCart()

            if (getCartResponse.isSuccessful) {
                _cartLiveData.postValue(
                    Resource.Success(
                        data = getCartResponse.body()?.data!!,
                        message = getCartResponse.body()?.message
                    )
                )
            } else {
                _cartLiveData.postValue(
                    Resource.Error(getErrorMessage(getCartResponse))
                )
            }
        }
    }

    fun incrementItemCount(itemId: String) {
        viewModelScope.launch {
            _cartLiveData.postValue((Resource.Loading()))

            val incrementItemCountRequestBody = IncrementItemCountRequestBody(itemId)
            val getCartResponse = cartRepository.incrementItemCount(incrementItemCountRequestBody)

            if (getCartResponse.isSuccessful) {
                _cartLiveData.postValue(
                    Resource.Success(
                        data = getCartResponse.body()?.data!!,
                        message = getCartResponse.body()?.message
                    )
                )
            } else {
                _cartLiveData.postValue(
                    Resource.Error(getErrorMessage(getCartResponse))
                )
            }
        }
    }

    fun decrementItemCount(itemId: String) {
        viewModelScope.launch {
            _cartLiveData.postValue((Resource.Loading()))

            val decrementItemCountRequestBody = DecrementItemCountRequestBody(itemId)
            val getCartResponse = cartRepository.decrementItemCount(decrementItemCountRequestBody)

            if (getCartResponse.isSuccessful) {
                _cartLiveData.postValue(
                    Resource.Success(
                        data = getCartResponse.body()?.data!!,
                        message = getCartResponse.body()?.message
                    )
                )
            } else {
                _cartLiveData.postValue(
                    Resource.Error(getErrorMessage(getCartResponse))
                )
            }
        }
    }

    fun deleteItemFromCart(itemId: String) {
        viewModelScope.launch {
            _cartLiveData.postValue((Resource.Loading()))

            val getCartResponse = cartRepository.deleteItemFromCart(itemId)

            if (getCartResponse.isSuccessful) {
                _cartLiveData.postValue(
                    Resource.Success(
                        data = getCartResponse.body()?.data!!,
                        message = getCartResponse.body()?.message
                    )
                )
            } else {
                _cartLiveData.postValue(
                    Resource.Error(getErrorMessage(getCartResponse))
                )
            }
        }
    }
}

