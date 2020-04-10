package com.example.e_commerce.ui.main.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIClient {

        //creating a Network Interceptor to add api_key in all the request as authInterceptor
        private val interceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()./*addQueryParameter("apiKey", API_KEY).*/build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
        // we are creating a networking client using OkHttp and add our authInterceptor.
        private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder().client(apiClient)
                .baseUrl("https://shushedevelop.000webhostapp.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        val retrofitInstance = getRetrofit().create(ApiInterface::class.java)

    }
