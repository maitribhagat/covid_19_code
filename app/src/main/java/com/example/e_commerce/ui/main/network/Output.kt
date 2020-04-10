package com.example.e_commerce.ui.main.network


/*todo First, we create a sealed class to handle network response.
   It can either be a success with the results coming with it or failure with an exception*/

sealed class Output<out T : Any>{
    data class Success<out T : Any>(val output : T) : Output<T>()
    data class Error(val exception: Exception)  : Output<Nothing>()
}