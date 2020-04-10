package com.example.e_commerce.ui.main.covid

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.ui.main.dashboard.model.DashboardData
import com.example.e_commerce.ui.main.dashboard.model.Menu
import com.example.e_commerce.ui.main.dashboard.repository.DashboardRepository
import com.example.e_commerce.ui.main.dashboard.viewmodel.BaseViewModel
import com.example.e_commerce.ui.main.network.APIClient
import com.example.e_commerce.ui.main.network.ApiInterface
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FaqViewModel : BaseViewModel() {
    //initialize news repo
    private val dashboardRepository : DashboardRepository = DashboardRepository(APIClient.retrofitInstance)
    val newsLiveData = MutableLiveData<MutableList<DashboardData>>()






    fun getFaq() {
        ///launch the coroutine scope
        coroutineScope.launch {
            //get latest news from news repo
            val menuList = dashboardRepository.getFaq()
            //post the value inside live data
            newsLiveData.postValue(menuList)

        }
    }
    fun cancelRequests() = coroutineScope.cancel()

}
