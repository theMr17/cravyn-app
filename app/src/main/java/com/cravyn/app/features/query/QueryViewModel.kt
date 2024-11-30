package com.cravyn.app.features.query

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cravyn.app.data.api.Resource
import com.cravyn.app.features.query.models.GetQueriesResponse
import com.cravyn.app.features.query.models.RaiseQueryRequestBody
import com.cravyn.app.util.ErrorResponseParserUtil.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueryViewModel @Inject constructor(
    private val queryRepository: QueryRepository
) : ViewModel() {
    private val _queriesLiveData: MutableLiveData<Resource<GetQueriesResponse>> = MutableLiveData()
    val queriesLiveData: LiveData<Resource<GetQueriesResponse>> get() = _queriesLiveData

    private val _raiseQueryLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val raiseQueryLiveData: LiveData<Resource<Unit>> get() = _raiseQueryLiveData

    fun getQueries() {
        viewModelScope.launch {
            _queriesLiveData.postValue(Resource.Loading())

            val queriesResponse = queryRepository.getQueries()

            if (queriesResponse.isSuccessful) {
                _queriesLiveData.postValue(
                    Resource.Success(
                        data = queriesResponse.body()!!.data,
                        message = queriesResponse.body()?.message
                    )
                )
            } else {
                _queriesLiveData.postValue(
                    Resource.Error(getErrorMessage(queriesResponse))
                )
            }
        }
    }

    fun raiseQuery(question: String) {
        viewModelScope.launch {
            _raiseQueryLiveData.postValue(Resource.Loading())

            val raiseQueryRequestBody = RaiseQueryRequestBody(question)

            val raiseQueryResponse = queryRepository.raiseQuery(raiseQueryRequestBody)

            if (raiseQueryResponse.isSuccessful) {
                _raiseQueryLiveData.postValue(
                    Resource.Success(
                        data = raiseQueryResponse.body()!!.data,
                        message = raiseQueryResponse.body()?.message
                    )
                )
            } else {
                _raiseQueryLiveData.postValue(
                    Resource.Error(getErrorMessage(raiseQueryResponse))
                )
            }
        }
    }
}
