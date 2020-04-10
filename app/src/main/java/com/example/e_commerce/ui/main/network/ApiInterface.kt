package com.example.e_commerce.ui.main.network

import com.example.e_commerce.ui.main.dashboard.model.DashBoardMenuModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


        @GET("category.json")
        fun fetchDashBoardMenuData() : Deferred<Response<DashBoardMenuModel>>

        @GET("faq.json")
        fun fetchFaq() : Deferred<Response<DashBoardMenuModel>>

}