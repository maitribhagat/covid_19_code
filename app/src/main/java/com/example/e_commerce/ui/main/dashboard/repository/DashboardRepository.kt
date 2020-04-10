package com.example.e_commerce.ui.main.dashboard.repository

import com.example.e_commerce.ui.main.Utils.BaseRepository
import com.example.e_commerce.ui.main.dashboard.model.DashboardData
import com.example.e_commerce.ui.main.dashboard.model.Menu
import com.example.e_commerce.ui.main.network.APIClient
import com.example.e_commerce.ui.main.network.ApiInterface

//We can now create a News repository or any other repository
//and extend this base repository class to make a safe API call


class DashboardRepository(private val apiInterface: ApiInterface) : BaseRepository() {
    //get latest news using safe api call
    suspend fun getDashboardMenu() :  MutableList<DashboardData>?{
        return safeApiCall(
            //await the result of deferred type
            call = {apiInterface.fetchDashBoardMenuData().await()},
            error = "Error fetching news"
            //convert to mutable list
        )?.data?.toMutableList()
    }


    suspend fun getFaq() :  MutableList<DashboardData>?{
        return safeApiCall(
            //await the result of deferred type
            call = {apiInterface.fetchFaq().await()},
            error = "Error fetching news"
            //convert to mutable list
        )?.data?.toMutableList()
    }
}