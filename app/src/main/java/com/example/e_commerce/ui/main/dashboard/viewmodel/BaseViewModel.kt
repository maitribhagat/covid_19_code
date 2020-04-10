package com.example.e_commerce.ui.main.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.e_commerce.ui.main.network.APIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(){

    // Create a Coroutine scope using a job to be able to cancel when needed
    private val parentJob = Job()
//    The plus() operator helps you create a Set of CoroutineContext elements, which you associate with the coroutines in a particular scope.
     val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)

    /*todo create retrofit instance*/
    val client = APIClient.retrofitInstance

}