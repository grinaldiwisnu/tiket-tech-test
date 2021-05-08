package com.example.testtiket.network

import android.os.Parcelable
import com.example.testtiket.model.User
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {
    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") query: String?
    ): Result
}

@Parcelize
data class Result(
    val total_count: String,
    val incomplete_results: Boolean? = null,
    val items: List<User>
) : Parcelable