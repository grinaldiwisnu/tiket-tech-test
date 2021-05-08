package com.example.testtiket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.testtiket.model.User
import com.example.testtiket.network.ApiRequest
import com.example.testtiket.utils.Resource

class MainViewModel : ViewModel() {
    private val username: MutableLiveData<String> = MutableLiveData()

    val searchResult: LiveData<Resource<List<User>>> = Transformations
        .switchMap(username) {
            ApiRequest.searchUsers(it)
        }

    fun setSearch(query: String) {
        if (username.value == query) {
            return
        }
        username.value = query
    }
}