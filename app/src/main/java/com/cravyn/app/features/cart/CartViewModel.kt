package com.cravyn.app.features.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.cart.model.AddItemToCartResponse
import com.cravyn.app.features.cart.model.AddItemtoCartRequestBody
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
): ViewModel() {
    private val _addItemToCartLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val addItemToCartLiveData: LiveData<Resource<Unit>> = _addItemToCartLiveData

    fun addItemtoCart(itemId: String) {
       viewModelScope.launch {
           _addItemToCartLiveData.postValue(Resource.Loading())

           val addItemToCartRequestBody = AddItemtoCartRequestBody(itemId)
           val addItemToCartResponse = cartRepository.addItemtoCart(addItemToCartRequestBody)

           if(addItemToCartResponse.isSuccessful) {
                _addItemToCartLiveData.postValue(Resource.Success(
                    data = Unit,
                    message = addItemToCartResponse.body()?.message
                ))
           }
           else {
               _addItemToCartLiveData.postValue(
                   Resource.Error(getErrorMessage(addItemToCartResponse))
               )
           }
       }
    }

}