package com.example.e_commerce.ui.main.dashboard.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.ui.main.dashboard.model.DashboardData
import com.example.e_commerce.ui.main.dashboard.model.Menu
import com.example.e_commerce.ui.main.dashboard.repository.DashboardRepository
import com.example.e_commerce.ui.main.network.APIClient
import com.example.e_commerce.ui.main.network.ApiInterface
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DashboardViewModel : BaseViewModel() {
    var timer: CountDownTimer
    var curruntVisble = MutableLiveData<Int>()

    //initialize news repo
    private val dashboardRepository : DashboardRepository = DashboardRepository(APIClient.retrofitInstance)
    val newsLiveData = MutableLiveData<MutableList<DashboardData>>()



    init {

        curruntVisble.value = 0
        timer = object : CountDownTimer(3000, 3000) {
            override fun onFinish() {
                onNext()
            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }
        startTimer()
    }


    fun onNext() {
        curruntVisble.value = curruntVisble.value!!.plus(1)
        /*todo if you stop repeated scrolling comment below condition abd comment else block inside startTimer method*/
        if (curruntVisble.value!! == 3) {
            curruntVisble.value = 0
        }
        startTimer()
    }
    fun startTimer() {
        timer.cancel()
        if (curruntVisble.value!! < 2) {
            timer.start()
        } else {
            timer.start()

        }
    }



    fun getDashboardMenu() {
        ///launch the coroutine scope
        coroutineScope.launch {
            //get latest news from news repo
            val menuList = dashboardRepository.getDashboardMenu()
            //post the value inside live data
            newsLiveData.postValue(menuList)

        }
    }
    fun cancelRequests() = coroutineScope.cancel()

}
