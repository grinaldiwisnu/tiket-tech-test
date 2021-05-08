package com.example.testtiket.network

import androidx.lifecycle.liveData
import com.example.testtiket.utils.Resource
import kotlinx.coroutines.Dispatchers

object ApiRequest {
    fun searchUsers(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val userSearch = Retrofit.apiClient.getSearchUser(query)
            emit(Resource.success(userSearch.items))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error"))
        }
    }
}