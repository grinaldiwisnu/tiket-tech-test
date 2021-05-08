package com.example.testtiket.network

import com.example.testtiket.utils.AUTH_TOKEN
import com.example.testtiket.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header(
                        "Authorization",
                        AUTH_TOKEN
                    ) // REPLACE GITHUB_API_KEY WITH YOUR GITHUB API KEY
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiClient: Endpoints by lazy {
        retrofitBuilder.build().create(Endpoints::class.java)
    }
}