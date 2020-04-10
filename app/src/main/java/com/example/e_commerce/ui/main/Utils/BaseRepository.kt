package com.example.e_commerce.ui.main.Utils

import android.util.Log
import com.example.e_commerce.ui.main.network.Output
import retrofit2.Response
import java.io.IOException


/*todo We then create a base repository class to handle the safe API calls.*/

//Here, we have two suspending higher-order functions.
// They both take a suspending function that returns a response(This will be the JSON response from the API)
//and an error message that will be displayed when an error occurs and there is no response.
open class BaseRepository {


    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = allApiDataOutput(call, error)
        var output : T? = null
        when(result){
            is Output.Success ->
                output = result.output
            is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
        }
        return output

    }


//    The newsApiOutput() function will try to get a response from the API.
//    if it is successful, then we call the success data class in our sealed class and
//    pass in the content of the response. If it is a failure, we call the Error data class in
//    our sealed class and pass in an IOException with the error message. We use this newsApiOutput()
//    function in our safeApiCall() function and return the output.


    private suspend fun<T : Any> allApiDataOutput(call: suspend()-> Response<T> , error: String) : Output<T>{
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }
}